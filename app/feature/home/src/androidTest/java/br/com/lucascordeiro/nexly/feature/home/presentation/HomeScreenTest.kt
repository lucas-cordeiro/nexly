package br.com.lucascordeiro.nexly.feature.home.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import br.com.lucascordeiro.nexly.feature.home.presentation.model.SortOption
import br.com.lucascordeiro.nexly.feature.home.stub.ExchangeStub
import br.com.lucascordeiro.nexly.shared.test.di.KoinTestRule
import br.com.lucascordeiro.nexly.shared.ui.error.ErrorState
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val action: Channel<HomeUiAction> = Channel(Channel.Factory.BUFFERED)
    private val state: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    private var viewModel = mockk<HomeViewModel>(relaxed = true)

    val exchange = ExchangeStub.exchangeUi

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = mutableListOf(module { viewModel { viewModel } })
    )

    @Before
    fun setup() {
        every { viewModel.action } returns action.consumeAsFlow()
        every { viewModel.state } returns state

        composeTestRule.setContent {
            HomeScreen()
        }
    }

    @Test
    fun whenLaunchedThenShouldShowExchanges() {
        state.update { state -> state.copy(exchanges = listOf(exchange)) }

        HomeScreenRobot(composeTestRule)
            .isTitleDisplayed()
            .isSortDisplayed()
            .isSortOptionDisplayed("Name")
            .isSortOptionDisplayed("ID")
            .isSortOptionDisplayed("Volume")
            .isExchangeDisplayed(exchange)
    }


    @Test
    fun whenClickedSortOptionThenShouldCallClickedSortOption() {
        state.update { state -> state.copy(exchanges = listOf(exchange)) }

        val option = SortOption(SortOption.Field.ID)

        HomeScreenRobot(composeTestRule)
            .isTitleDisplayed()
            .isSortDisplayed()
            .isSortOptionDisplayed(option.label)
            .clickSortOption(option.label)

        verify(exactly = 1) { viewModel.clickedSortOption(option) }
    }

    @Test
    fun whenClickExchangeThenShouldCallClickedExchange() {
        state.update { state -> state.copy(exchanges = listOf(exchange)) }

        HomeScreenRobot(composeTestRule)
            .isExchangeDisplayed(exchange)
            .clickExchange(exchange)

        verify(exactly = 1) { viewModel.clickedExchange(exchange) }
    }

    @Test
    fun whenGenericErrorThenShouldShowGenericFeedbackAlert() {
        state.update { state ->
            state.copy(error = ErrorState.Show.Generic)
        }

        HomeScreenRobot(composeTestRule)
            .isTitleDisplayed()
            .isSortDisplayed()
            .isSortOptionDisplayed("Name")
            .isSortOptionDisplayed("ID")
            .isSortOptionDisplayed("Volume")
            .isFeedbackDisplayed(ErrorState.Show.Generic.message)
    }

    @Test
    fun whenNetworkErrorThenShouldShowNetworkFeedbackAlert() {
        state.update { state ->
            state.copy(error = ErrorState.Show.Network)
        }

        HomeScreenRobot(composeTestRule)
            .isTitleDisplayed()
            .isSortDisplayed()
            .isSortOptionDisplayed("Name")
            .isSortOptionDisplayed("ID")
            .isSortOptionDisplayed("Volume")
            .isFeedbackDisplayed(ErrorState.Show.Network.message)
    }

    @Test
    fun whenNoInternetErrorThenShouldShowNoInternetFeedbackAlert() {
        state.update { state ->
            state.copy(error = ErrorState.Show.NoInternetConnection)
        }

        HomeScreenRobot(composeTestRule)
            .isTitleDisplayed()
            .isSortDisplayed()
            .isSortOptionDisplayed("Name")
            .isSortOptionDisplayed("ID")
            .isSortOptionDisplayed("Volume")
            .isFeedbackDisplayed(ErrorState.Show.NoInternetConnection.message)
    }
}