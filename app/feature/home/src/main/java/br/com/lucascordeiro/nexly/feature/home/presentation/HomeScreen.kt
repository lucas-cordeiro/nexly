package br.com.lucascordeiro.nexly.feature.home.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.lucascordeiro.nexly.feature.home.presentation.model.ExchangeUi
import br.com.lucascordeiro.nexly.feature.home.presentation.model.SortOption
import br.com.lucascordeiro.nexly.shared.navigation.MainNavigation
import br.com.lucascordeiro.nexly.shared.navigation.routes.DetailsRoute
import br.com.lucascordeiro.nexly.shared.ui.components.UITextChip
import br.com.lucascordeiro.nexly.shared.ui.components.UiFeedback
import br.com.lucascordeiro.nexly.shared.ui.components.UiLoading
import br.com.lucascordeiro.nexly.shared.ui.components.UiToolbar
import br.com.lucascordeiro.nexly.shared.ui.theme.Gray12
import br.com.lucascordeiro.nexly.shared.ui.theme.Gray18
import br.com.lucascordeiro.nexly.shared.ui.theme.NexlyTheme
import br.com.lucascordeiro.nexly.shared.ui.theme.Primary
import io.github.lucascordeiro.ymir.core.utils.LifecycleUtils.ObserveActions
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val lazyState = rememberLazyListState()

    fun handleAction(
        action: HomeUiAction,
    ) {
        when (action) {
            is HomeUiAction.NavigateToDetails -> navigateToDetails(action.id)
            is HomeUiAction.ScrollToTop -> {
                scope.launch { lazyState.animateScrollToItem(0) }
            }
        }
    }

    ObserveActions(viewModel, ::handleAction)

    LazyColumn(
        state = lazyState
    ) {

        stickyHeader {
            Column {
                UiToolbar(title = "Exchanges")

                Sort(
                    options = state.sortOptions,
                    onOptionClick = viewModel::clickedSortOption,
                    modifier = Modifier
                        .background(NexlyTheme.colorScheme.background)
                        .padding(top = 24.dp, bottom = 8.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }

        item { UiLoading(isVisible = state.isLoading) }

        itemsIndexed(
            items = state.exchanges,
            key = { _, exchange -> exchange.id }
        ) { index, exchange ->
            ExchangeListItem(
                exchange = exchange,
                onClick = { viewModel.clickedExchange(exchange) },
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        bottom = if (index == state.exchanges.lastIndex) 24.dp else 0.dp
                    )
                    .padding(horizontal = 16.dp)
            )
        }
    }

    UiFeedback(
        errorState = state.error,
        onDismissRequest = viewModel::clickedError
    )
}

@Composable
private fun Sort(
    options: List<SortOption>,
    onOptionClick: (SortOption) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        stickyHeader {
            Text(
                text = "Sort",
                style = NexlyTheme.typography.body,
                color = Gray18,
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        items(options) { option ->
            SortOptionButton(
                label = option.label,
                isSelected = option.isSelected,
                onClick = { onOptionClick(option) }
            )
        }
    }
}

@Composable
private fun SortOptionButton(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tintColor by animateColorAsState(if (isSelected) Primary else Gray12)
    val backgroundColor by animateColorAsState(
        if (isSelected) Primary.copy(.1f) else Gray12.copy(
            .05f
        )
    )

    Text(
        text = label,
        style = NexlyTheme.typography.body,
        color = tintColor,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = backgroundColor)
            .clickable(onClick = onClick)
            .padding(
                horizontal = 12.dp,
                vertical = 4.dp
            )
    )
}

@Composable
private fun ExchangeListItem(
    exchange: ExchangeUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = exchange.name,
                    style = NexlyTheme.typography.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Gray18,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                UITextChip(
                    text = "ID: ${exchange.id}",
                    color = exchange.color
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Volume: ${exchange.volume}",
                style = NexlyTheme.typography.subtitle,
                color = Gray12
            )
        }
    }
}


private fun navigateToDetails(id: String) {
    MainNavigation.navigateTo(DetailsRoute(id))
}