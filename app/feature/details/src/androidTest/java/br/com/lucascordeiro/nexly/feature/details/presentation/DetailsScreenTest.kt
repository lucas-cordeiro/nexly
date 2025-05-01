package br.com.lucascordeiro.nexly.feature.details.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import br.com.lucascordeiro.nexly.feature.details.stub.ExchangeStub
import br.com.lucascordeiro.nexly.shared.navigation.routes.DetailsRoute
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

internal class DetailsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val action: Channel<DetailsUiAction> = Channel(Channel.Factory.BUFFERED)
    private val state: MutableStateFlow<DetailsUiState> = MutableStateFlow(DetailsUiState())
    private var viewModel = mockk<DetailsViewModel>(relaxed = true)

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
            DetailsScreen(route = DetailsRoute(exchange.id))
        }
    }


    @Test
    fun whenLaunchedThenShouldShowExchange() {

        DetailsScreenRobot(composeTestRule)
            .isTitleDisplayed("Loading...")

        state.update { state -> state.copy(exchange = exchange) }

        DetailsScreenRobot(composeTestRule)
            .isTitleDisplayed(exchange.name)
            .isExchangeIDDisplayed(exchange.id)
            .isExchangeVolumeDisplayed(exchange.volume)
    }

    @Test
    fun whenClickWebsiteThenShouldCallClickedExchange() {
        state.update { state -> state.copy(exchange = exchange) }

        DetailsScreenRobot(composeTestRule)
            .isTitleDisplayed(exchange.name)
            .isExchangeIDDisplayed(exchange.id)
            .clickWebsite()

        verify(exactly = 1) { viewModel.clickedWebsite() }
    }

    @Test
    fun whenGenericErrorThenShouldShowGenericFeedbackAlert() {
        state.update { state ->
            state.copy(error = ErrorState.Show.Generic)
        }

        DetailsScreenRobot(composeTestRule)
            .isTitleDisplayed("Loading...")
            .isFeedbackDisplayed(ErrorState.Show.Generic.message)
    }

    @Test
    fun whenNetworkErrorThenShouldShowNetworkFeedbackAlert() {
        state.update { state ->
            state.copy(error = ErrorState.Show.Network)
        }

        DetailsScreenRobot(composeTestRule)
            .isTitleDisplayed("Loading...")
            .isFeedbackDisplayed(ErrorState.Show.Network.message)
    }

    @Test
    fun whenNoInternetErrorThenShouldShowNoInternetFeedbackAlert() {
        state.update { state ->
            state.copy(error = ErrorState.Show.NoInternetConnection)
        }

        DetailsScreenRobot(composeTestRule)
            .isTitleDisplayed("Loading...")
            .isFeedbackDisplayed(ErrorState.Show.NoInternetConnection.message)
    }
}