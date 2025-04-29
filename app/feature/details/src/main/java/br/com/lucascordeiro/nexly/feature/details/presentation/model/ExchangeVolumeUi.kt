package br.com.lucascordeiro.nexly.feature.details.presentation.model

import br.com.lucascordeiro.nexly.feature.details.domain.model.ExchangeVolume
import br.com.lucascordeiro.nexly.shared.ui.utils.formatVolume

internal data class ExchangeVolumeUi(
    val lastHour: String,
    val lastDay: String,
    val lastMonth: String
) {
    companion object {
        fun fromDomain(exchangeVolume: ExchangeVolume): ExchangeVolumeUi {
            return ExchangeVolumeUi(
                lastHour = formatVolume(exchangeVolume.lastHour),
                lastDay = formatVolume(exchangeVolume.lastDay),
                lastMonth = formatVolume(exchangeVolume.lastMonth)
            )
        }
    }
}
