package br.com.lucascordeiro.nexly.feature.details.presentation.model

import br.com.lucascordeiro.nexly.feature.details.domain.model.ActivityPeriods
import br.com.lucascordeiro.nexly.shared.ui.utils.toFormattedString
import java.util.Date

data class ActivityPeriodsUi(
    val quoteStart: String?,
    val quoteEnd: String?,
    val orderbookStart: String?,
    val orderbookEnd: String?,
    val tradeStart: String?,
    val tradeEnd: String?
) {
    companion object {
        fun fromDomain(activityPeriods: ActivityPeriods): ActivityPeriodsUi {
            return ActivityPeriodsUi(
                quoteStart = activityPeriods.quoteStart?.toFormattedString(),
                quoteEnd = activityPeriods.quoteEnd?.toFormattedString(),
                orderbookStart = activityPeriods.orderbookStart?.toFormattedString(),
                orderbookEnd = activityPeriods.orderbookEnd?.toFormattedString(),
                tradeStart = activityPeriods.tradeStart?.toFormattedString(),
                tradeEnd = activityPeriods.tradeEnd?.toFormattedString()
            )
        }
    }
}
