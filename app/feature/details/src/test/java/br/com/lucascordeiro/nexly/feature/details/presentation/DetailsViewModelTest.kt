package br.com.lucascordeiro.nexly.feature.details.presentation

import br.com.lucascordeiro.nexly.feature.details.domain.model.Exchange
import br.com.lucascordeiro.nexly.feature.details.domain.usecase.GetExchangeByIdUseCase
import br.com.lucascordeiro.nexly.feature.details.stub.ExchangeStub
import br.com.lucascordeiro.nexly.shared.network.error.NetworkException
import br.com.lucascordeiro.nexly.shared.test.viewmodel.MainDispatcherRule
import br.com.lucascordeiro.nexly.shared.ui.error.ErrorState
import io.github.lucascordeiro.ymir.test.ViewModelObserver
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class DetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var getExchangeByIdUseCase: GetExchangeByIdUseCase = mockk()

    private lateinit var viewModel: DetailsViewModel

    private val observer by lazy {
        ViewModelObserver(
            viewModel = viewModel,
            testDispatcher = mainDispatcherRule.testDispatcher
        )
    }

    @Test
    fun `given success response when initializing ViewModel then should set exchange`() = runTest {
        initializeViewModel(mock = { ExchangeStub.exchange })

        observer.start()
        // Give
        val expectedInitialState = DetailsUiState()
        val expectedState = DetailsUiState(exchange = ExchangeStub.exchangeUi)

        // When
        advanceUntilIdle()

        // Then
        val state = observer.state
        assertEquals(expectedInitialState, state.first())
        assertEquals(expectedState, state.last())
        coVerify(exactly = 1) { getExchangeByIdUseCase(ExchangeStub.exchange.id) }

        observer.stop()
    }

    @Test
    fun `given error response when initializing ViewModel then should set error`() = runTest {
        initializeViewModel(
            mock = { throw NetworkException(code = 404, message = "Some error") }
        )

        observer.start()
        // Give
        val expectedInitialState = DetailsUiState()
        val expectedState = DetailsUiState(error = ErrorState.Show.Network)

        // When
        advanceUntilIdle()

        // Then
        val state = observer.state
        assertEquals(expectedInitialState, state.first())
        assertEquals(expectedState, state.last())
        coVerify(exactly = 1) { getExchangeByIdUseCase(ExchangeStub.exchange.id) }

        observer.stop()
    }

    @Test
    fun `when clickedBack then should send NavigateBack action`() = runTest {
        initializeViewModel(mock = { ExchangeStub.exchange })

        observer.start()
        // Give
        val expectedAction = DetailsUiAction.NavigateBack

        // When
        viewModel.clickedBack()
        advanceUntilIdle()

        // Then
        val action = observer.action
        assertEquals(expectedAction, action.last())

        observer.stop()
    }

    @Test
    fun `when clickedError then should set error to dismiss and fetch data`() = runTest {
        initializeViewModel(mock = { throw NetworkException(code = 404, message = "Some error") })

        observer.start()
        // Give
        val expectedInitialState = DetailsUiState()
        val expectedState = DetailsUiState(
            error = ErrorState.Dismiss,
            exchange = ExchangeStub.exchangeUi
        )

        // When
        coEvery { getExchangeByIdUseCase(ExchangeStub.exchange.id) } answers { ExchangeStub.exchange }
        viewModel.clickedError()
        advanceUntilIdle()

        // Then
        val state = observer.state
        assertEquals(expectedInitialState, state.first())
        assertEquals(expectedState, state.last())

        coVerify(exactly = 2) { getExchangeByIdUseCase(ExchangeStub.exchange.id) }

        observer.stop()
    }

    private fun initializeViewModel(
        exchangeId: String = ExchangeStub.exchangeUi.id,
        mock: () -> Exchange,
    ) {
        coEvery { getExchangeByIdUseCase(exchangeId) } answers { mock() }

        viewModel = DetailsViewModel(
            exchangeId = exchangeId,
            getExchangeByIdUseCase = getExchangeByIdUseCase,
            ioDispatcher = mainDispatcherRule.testDispatcher
        )
    }
}