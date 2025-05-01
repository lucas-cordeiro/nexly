package br.com.lucascordeiro.nexly.feature.details.stub

import br.com.lucascordeiro.nexly.feature.details.presentation.model.ActivityPeriodsUi
import br.com.lucascordeiro.nexly.feature.details.presentation.model.ExchangeUi
import br.com.lucascordeiro.nexly.feature.details.presentation.model.ExchangeVolumeUi

internal object ExchangeStub {
    val exchangeUi = ExchangeUi(
        id = "BINANCE",
        name = "Binance",
        volume = ExchangeVolumeUi(
            lastHour = "US$ 23,1 mi",
            lastDay = "US$ 12,1 bi",
            lastMonth = "US$ 505,1 bi"
        ),
        rank = "#2",
        activityPeriods = ActivityPeriodsUi(
            quoteStart = "18/12/2017",
            quoteEnd = "22/04/2025",
            orderbookStart = "18/12/2017",
            orderbookEnd = "22/04/2025",
            tradeStart = "14/07/2017",
            tradeEnd = "19/04/2025"
        ),
        website = "https://www.binance.com/"
    )
}