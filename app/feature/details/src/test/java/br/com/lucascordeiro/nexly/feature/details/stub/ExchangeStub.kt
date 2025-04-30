package br.com.lucascordeiro.nexly.feature.details.stub

import br.com.lucascordeiro.nexly.feature.details.domain.model.ActivityPeriods
import br.com.lucascordeiro.nexly.feature.details.domain.model.Exchange
import br.com.lucascordeiro.nexly.feature.details.domain.model.ExchangeVolume
import br.com.lucascordeiro.nexly.feature.details.presentation.model.ActivityPeriodsUi
import br.com.lucascordeiro.nexly.feature.details.presentation.model.ExchangeUi
import br.com.lucascordeiro.nexly.feature.details.presentation.model.ExchangeVolumeUi
import java.util.Date

internal object ExchangeStub {
    val exchange = Exchange(
        id = "BINANCE",
        name = "Binance",
        volume = ExchangeVolume(
            lastHour = 23055328.11,
            lastDay = 12098309304.78,
            lastMonth = 505115715291.93,
        ),
        rank = 2.0,
        activityPeriods = ActivityPeriods(
            quoteStart = Date(1513575006000),
            quoteEnd = Date(1745303406000),
            orderbookStart = Date(1513575006000),
            orderbookEnd = Date(1745303406000),
            tradeStart = Date(1500013806000),
            tradeEnd = Date(1745044206000)
        ),
        website = "https://www.binance.com/"
    )

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