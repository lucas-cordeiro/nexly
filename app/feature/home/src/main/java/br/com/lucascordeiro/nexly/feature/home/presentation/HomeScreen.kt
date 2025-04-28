package br.com.lucascordeiro.nexly.feature.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.lucascordeiro.nexly.shared.navigation.MainNavigation
import br.com.lucascordeiro.nexly.shared.navigation.routes.DetailsRoute
import io.github.lucascordeiro.ymir.core.utils.LifecycleUtils.ObserveActions
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveActions(viewModel, ::handleAction)

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            AnimatedVisibility(
                visible = state.isLoading
            ) { CircularProgressIndicator() }
        }

        items(
            items = state.exchanges,
            key = { exchange -> exchange.id }
        ) { exchange ->
            TextButton(
                onClick = { viewModel.clickedExchange(exchange) }
            ) {
                Text(
                    text = exchange.name,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

internal fun handleAction(action: HomeUiAction) {
    when (action) {
        is HomeUiAction.NavigateToDetails -> navigateToDetails(action.id)
    }
}

private fun navigateToDetails(id: String) {
    MainNavigation.navigateTo(DetailsRoute(id))
}