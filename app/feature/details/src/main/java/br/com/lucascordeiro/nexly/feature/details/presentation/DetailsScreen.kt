package br.com.lucascordeiro.nexly.feature.details.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.lucascordeiro.nexly.feature.details.presentation.model.ActivityPeriodsUi
import br.com.lucascordeiro.nexly.feature.details.presentation.model.ExchangeVolumeUi
import br.com.lucascordeiro.nexly.shared.navigation.MainNavigation
import br.com.lucascordeiro.nexly.shared.navigation.routes.DetailsRoute
import br.com.lucascordeiro.nexly.shared.ui.components.UITextChip
import br.com.lucascordeiro.nexly.shared.ui.components.UiFeedback
import br.com.lucascordeiro.nexly.shared.ui.components.UiLoading
import br.com.lucascordeiro.nexly.shared.ui.components.UiToolbar
import br.com.lucascordeiro.nexly.shared.ui.theme.Blue01
import br.com.lucascordeiro.nexly.shared.ui.theme.Gray02
import br.com.lucascordeiro.nexly.shared.ui.theme.Gray12
import br.com.lucascordeiro.nexly.shared.ui.theme.Gray18
import br.com.lucascordeiro.nexly.shared.ui.theme.Green01
import br.com.lucascordeiro.nexly.shared.ui.theme.NexlyTheme
import br.com.lucascordeiro.nexly.shared.ui.theme.Primary
import io.github.lucascordeiro.ymir.core.utils.LifecycleUtils.ObserveActions
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailsScreen(route: DetailsRoute) {
    val viewModel: DetailsViewModel = koinViewModel() { parametersOf(route.id) }
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    fun handleAction(action: DetailsUiAction) {
        when (action) {
            is DetailsUiAction.NavigateBack -> MainNavigation.navigateBack()
            is DetailsUiAction.OpenWebsite -> openWebsite(action.url, context)
        }
    }

    ObserveActions(viewModel, ::handleAction)

    Column {
        UiToolbar(
            title = state.exchange?.name ?: "Loading...",
            onBackClick = viewModel::clickedBack
        )

        UiLoading(state.isLoading)

        AnimatedContent(
            targetState = state.exchange,
            transitionSpec = {
                expandVertically() + fadeIn() togetherWith
                        shrinkVertically() + fadeOut()
            },
        ) { exchange ->
            if (exchange != null) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Header(
                        volume = exchange.volume,
                        id = exchange.id
                    )

                    Rank(
                        rank = exchange.rank,
                        modifier = Modifier
                            .padding(top = 24.dp)
                    )

                    ActivityPeriods(
                        activityPeriodsUi = exchange.activityPeriods,
                        modifier = Modifier
                            .padding(top = 24.dp)
                    )

                    Website(
                        onClick = viewModel::clickedWebsite,
                        modifier = Modifier
                            .padding(top = 24.dp)
                    )
                }
            }
        }
    }

    UiFeedback(
        errorState = state.error,
        onDismissRequest = viewModel::clickedError
    )
}

@Composable
private fun Header(
    volume: ExchangeVolumeUi,
    id: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Text(
                text = "Volume",
                style = NexlyTheme.typography.title,
                color = Gray18
            )

            Spacer(modifier = Modifier.weight(1f))

            UITextChip(
                text = "ID: $id",
                color = Primary,
                modifier = Modifier.testTag(DetailsScreenTags.exchangeId)
            )
        }

        VolumeRow(
            label = "Hourly",
            value = volume.lastHour,
            modifier = Modifier.testTag(DetailsScreenTags.exchangVolumeHourly)
        )

        VolumeRow(
            label = "Daily",
            value = volume.lastDay,
            modifier = Modifier.testTag(DetailsScreenTags.exchangVolumeDaily)
        )

        VolumeRow(
            label = "Monthly",
            value = volume.lastMonth,
            modifier = Modifier.testTag(DetailsScreenTags.exchangVolumeMonthly)
        )
    }
}

@Composable
private fun VolumeRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = NexlyTheme.typography.subtitle,
                color = Gray12
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = value,
                style = NexlyTheme.typography.headline,
                color = Gray12
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        HorizontalDivider(color = Gray02)
    }
}

@Composable
private fun Rank(
    rank: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = "Rank",
            style = NexlyTheme.typography.title,
            color = Gray18
        )

        Spacer(modifier = Modifier.weight(1f))

        UITextChip(
            text = rank,
            color = Gray12
        )
    }
}

@Composable
private fun ActivityPeriods(
    activityPeriodsUi: ActivityPeriodsUi,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Activity Periods",
            style = NexlyTheme.typography.title,
            color = Gray18
        )

        Spacer(modifier = Modifier.height(10.dp))


        val quoteStart = activityPeriodsUi.quoteStart
        val quoteEnd = activityPeriodsUi.quoteEnd

        if (!quoteStart.isNullOrBlank() && !quoteEnd.isNullOrBlank()) {
            ActivityPeriod(
                category = "Quote",
                start = quoteStart,
                end = quoteEnd
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        val orderbookStart = activityPeriodsUi.orderbookStart
        val orderbookEnd = activityPeriodsUi.orderbookEnd

        if (!orderbookStart.isNullOrBlank() && !orderbookEnd.isNullOrBlank()) {
            ActivityPeriod(
                category = "Orderbook",
                start = orderbookStart,
                end = orderbookEnd
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        val tradeStart = activityPeriodsUi.tradeStart
        val tradeEnd = activityPeriodsUi.tradeEnd

        if (!tradeStart.isNullOrBlank() && !tradeEnd.isNullOrBlank()) {
            ActivityPeriod(
                category = "Trade",
                start = tradeStart,
                end = tradeEnd
            )
        }
    }
}

@Composable
private fun ActivityPeriod(
    category: String,
    start: String,
    end: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Row {
            Text(
                text = category,
                style = NexlyTheme.typography.subtitle,
                color = Gray12
            )

            Spacer(modifier = Modifier.weight(1f))

            UITextChip(
                text = start,
                color = Blue01
            )

            Spacer(modifier = Modifier.width(4.dp))

            UITextChip(
                text = "~",
                color = Gray12
            )

            Spacer(modifier = Modifier.width(4.dp))

            UITextChip(
                text = end,
                color = Green01
            )
        }
    }
}

@Composable
private fun Website(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = "Site",
            style = NexlyTheme.typography.title,
            color = Gray18
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Open",
            style = NexlyTheme.typography.subtitle,
            textDecoration = TextDecoration.Underline,
            color = Blue01,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .clickable { onClick() }
                .padding(4.dp)
        )
    }
}

private fun openWebsite(
    url: String,
    context: Context
) {
    context.startActivity(
        Intent(Intent.ACTION_VIEW).apply { data = url.toUri() }
    )
}
