package br.com.lucascordeiro.nexly.feature.home.presentation

import br.com.lucascordeiro.nexly.feature.home.data.network.RemoteDataSource
import br.com.lucascordeiro.nexly.feature.home.data.network.RemoteDataSourceImpl
import br.com.lucascordeiro.nexly.feature.home.data.repository.ExchangeRepositoryImpl
import br.com.lucascordeiro.nexly.feature.home.domain.repository.ExchangeRepository
import br.com.lucascordeiro.nexly.feature.home.domain.usecase.GetAllExchangesUseCase
import br.com.lucascordeiro.nexly.feature.home.presentation.model.SortOption
import br.com.lucascordeiro.nexly.feature.home.stub.ExchangeStub
import br.com.lucascordeiro.nexly.shared.test.network.HttpClientMockBuilder
import br.com.lucascordeiro.nexly.shared.test.network.HttpRequestHandler
import br.com.lucascordeiro.nexly.shared.test.network.ResponseData
import br.com.lucascordeiro.nexly.shared.test.network.errorResponse
import br.com.lucascordeiro.nexly.shared.test.network.successResponse
import br.com.lucascordeiro.nexly.shared.test.viewmodel.MainDispatcherRule
import br.com.lucascordeiro.nexly.shared.ui.error.ErrorState
import io.github.lucascordeiro.ymir.test.ViewModelObserver
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var httpRequestHandler: HttpRequestHandler = mockk()

    private lateinit var viewModel: HomeViewModel

    private lateinit var getAllExchangesUseCase: GetAllExchangesUseCase

    private val observer by lazy {
        ViewModelObserver(
            viewModel = viewModel,
            testDispatcher = mainDispatcherRule.testDispatcher
        )
    }

    @Before
    fun setup() {
        val httpClient = HttpClientMockBuilder()
            .setHandler(httpRequestHandler)
            .setDispatcher(mainDispatcherRule.testDispatcher)
            .build()

        val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl(
            httpClient = httpClient
        )
        val repository: ExchangeRepository = ExchangeRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
        getAllExchangesUseCase = GetAllExchangesUseCase(repository::getAll)
    }

    @Test
    fun `given success response when initializing ViewModel then should set exchanges`() = runTest {
        initializeViewModel(
            responseData = successResponse(ExchangeStub.exchangesJson)
        )
        observer.start()

        // Given
        val expectedInitialState = HomeUiState()
        val expectedSortOptions = ExchangeStub.expectedSortOptions(SortOption.Field.Name)
        val expectedExchange = ExchangeStub.exchangeUi

        // When
        advanceUntilIdle()

        // Then
        val state = observer.state
        val lastState = state.last()
        val actualExchange = lastState.exchanges.first()

        assertEquals(expectedInitialState, state.first())
        assertEquals(expectedSortOptions, lastState.sortOptions)
        // Because of the randomness of the color need to check each field
        assertEquals(expectedExchange.id, actualExchange.id)
        assertEquals(expectedExchange.name, actualExchange.name)
        assertEquals(expectedExchange.volume, actualExchange.volume)
        assertEquals(expectedExchange.originalVolume, actualExchange.originalVolume)

        observer.stop()
    }

    @Test
    fun `given error response when initializing ViewModel then should set error state`() = runTest {
        initializeViewModel(responseData = errorResponse())
        observer.start()

        // Given
        val expectedInitialState = HomeUiState()
        val expectedErrorState = ErrorState.Show.Network

        // When
        advanceUntilIdle()

        // Then
        val state = observer.state
        assertEquals(expectedInitialState, state.first())
        assertEquals(expectedErrorState, state.last().error)

        observer.stop()
    }

    @Test
    fun `when clickedExchange then should send NavigateToDetails action`() = runTest {
        initializeViewModel(
            responseData = successResponse(ExchangeStub.exchangesJson)
        )

        observer.start()

        // Given
        val expectedAction = HomeUiAction.NavigateToDetails(ExchangeStub.exchangeUi.id)

        // When
        viewModel.clickedExchange(ExchangeStub.exchangeUi)
        advanceUntilIdle()

        // Then
        val action = observer.action
        assertEquals(expectedAction, action.last())

        observer.stop()
    }

    @Test
    fun `when clickedSortOption then should set sort options`() = runTest {
        initializeViewModel(
            responseData = successResponse(ExchangeStub.exchangesJson)
        )

        observer.start()

        // Given
        val expectedInitialState = HomeUiState()
        val expectedSortOptions = ExchangeStub.expectedSortOptions(SortOption.Field.Volume)
        val selectedOption =
            expectedInitialState.sortOptions.first { it.field == SortOption.Field.Volume }

        // When
        viewModel.clickedSortOption(selectedOption)
        advanceUntilIdle()

        // Then
        val state = observer.state
        assertEquals(expectedInitialState, state.first())
        val lastState = state.last()
        assertEquals(expectedSortOptions, lastState.sortOptions)

        observer.stop()
    }

    @Test
    fun `when clickedError then should set error state to dismiss`() = runTest {
        initializeViewModel(
            responseData = successResponse(ExchangeStub.exchangesJson)
        )

        observer.start()

        // Given
        val expectedInitialState = HomeUiState()
        val expectedErrorState = ErrorState.Dismiss

        // When
        viewModel.clickedError()
        advanceUntilIdle()

        // Then
        val state = observer.state
        assertEquals(expectedInitialState, state.first())
        assertEquals(expectedErrorState, state.last().error)

        observer.stop()
    }

    private fun initializeViewModel(
        responseData: ResponseData
    ) {
        mockResponse(responseData)

        viewModel = HomeViewModel(
            getAllExchangesUseCase = getAllExchangesUseCase,
            ioDispatcher = mainDispatcherRule.testDispatcher
        )
    }

    private fun mockResponse(
        responseData: ResponseData
    ) {
        every { httpRequestHandler.handle(any()) } answers { responseData }
    }
}