package br.com.lucascordeiro.nexly.feature.details.data.network.model

import br.com.lucascordeiro.nexly.feature.details.domain.model.ActivityPeriods
import br.com.lucascordeiro.nexly.feature.details.domain.model.Exchange
import br.com.lucascordeiro.nexly.feature.details.domain.model.ExchangeVolume
import br.com.lucascordeiro.nexly.shared.ui.utils.formatDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* 		"data_quote_start": "2017-12-18T00:00:00.0000000Z",
		"data_quote_end": "2025-04-22T00:00:00.0000000Z",
		"data_orderbook_start": "2017-12-18T00:00:00.0000000Z",
		"data_orderbook_end": "2025-04-22T00:00:00.0000000Z",
		"data_trade_start": "2017-07-14T00:00:00.0000000Z",
		"data_trade_end": "2025-04-19T00:00:00.0000000Z",*/
@Serializable
internal data class ExchangeResponse(
    @SerialName("exchange_id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("volume_1hrs_usd")
    val volumeHour: Double? = null,
    @SerialName("volume_1day_usd")
    val volumeDay: Double? = null,
    @SerialName("volume_1mth_usd")
    val volumeMonth: Double? = null,
    @SerialName("rank")
    val rank: Double? = null,
    @SerialName("data_quote_start")
    val dataQuoteStart: String? = null,
    @SerialName("data_quote_end")
    val dataQuoteEnd: String? = null,
    @SerialName("data_orderbook_start")
    val dataOrderbookStart: String? = null,
    @SerialName("data_orderbook_end")
    val dataOrderbookEnd: String? = null,
    @SerialName("data_trade_start")
    val dataTradeStart: String? = null,
    @SerialName("data_trade_end")
    val dataTradeEnd: String? = null,
    @SerialName("website")
    val website: String? = null
) {
    fun toDomain(): Exchange {

        return Exchange(
            id = id.orEmpty(),
            name = name.orEmpty(),
            volume = ExchangeVolume(
                lastHour = volumeHour ?: 0.0,
                lastDay = volumeDay ?: 0.0,
                lastMonth = volumeMonth ?: 0.0
            ),
            rank = rank ?: 0.0,
            activityPeriods = ActivityPeriods(
                quoteStart = formatDate(dataQuoteStart),
                quoteEnd = formatDate(dataQuoteEnd),
                orderbookStart = formatDate(dataOrderbookStart),
                orderbookEnd = formatDate(dataOrderbookEnd),
                tradeStart = formatDate(dataTradeStart),
                tradeEnd = formatDate(dataTradeEnd)
            ),
            website = website.orEmpty()
        )
    }
}