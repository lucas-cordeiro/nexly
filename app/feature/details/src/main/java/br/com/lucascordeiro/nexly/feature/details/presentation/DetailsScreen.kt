package br.com.lucascordeiro.nexly.feature.details.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.lucascordeiro.nexly.shared.navigation.routes.DetailsRoute
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailsScreen(route: DetailsRoute) {
    val viewModel: DetailsViewModel = koinViewModel() { parametersOf(route.id) }
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column {
        this.AnimatedVisibility(
            visible = state.isLoading
        ) {
            CircularProgressIndicator()
        }

        AnimatedContent(
            targetState = state.exchange,
            transitionSpec = {
                expandVertically() + fadeIn() togetherWith
                        shrinkVertically() + fadeOut()
            },
        ) { exchange ->
            if (exchange != null) {
                Column {
                    Text(text = exchange.id)
                    Text(text = exchange.name)
                    Text(text = "${exchange.volume}")
                }
            }
        }
    }
}