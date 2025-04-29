package br.com.lucascordeiro.nexly.feature.details.presentation.model

import br.com.lucascordeiro.nexly.feature.details.domain.model.Exchange
import kotlin.math.roundToInt

internal data class ExchangeUi(
    val id: String,
    val name: String,
    val volume: ExchangeVolumeUi,
    val rank: String,
    val activityPeriods: ActivityPeriodsUi,
    val website: String
) {
    companion object {
        fun fromDomain(exchange: Exchange): ExchangeUi {
            return ExchangeUi(
                id = exchange.id,
                name = exchange.name,
                volume = ExchangeVolumeUi.fromDomain(exchange.volume),
                rank = "#${exchange.rank.roundToInt()}",
                activityPeriods = ActivityPeriodsUi.fromDomain(
                    exchange.activityPeriods
                ),
                website = exchange.website
            )
        }
    }
}