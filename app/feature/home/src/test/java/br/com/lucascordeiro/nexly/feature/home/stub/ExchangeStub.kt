package br.com.lucascordeiro.nexly.feature.home.stub

import androidx.compose.ui.graphics.Color
import br.com.lucascordeiro.nexly.feature.home.presentation.HomeUiState
import br.com.lucascordeiro.nexly.feature.home.presentation.model.ExchangeUi
import br.com.lucascordeiro.nexly.feature.home.presentation.model.SortOption

internal object ExchangeStub {
    val exchangesJson = """
    [
       {
          "exchange_id":"BINANCE",
          "website":"https://www.binance.com/",
          "name":"Binance",
          "data_quote_start":"2017-12-18T00:00:00.0000000Z",
          "data_quote_end":"2025-04-22T00:00:00.0000000Z",
          "data_orderbook_start":"2017-12-18T00:00:00.0000000Z",
          "data_orderbook_end":"2025-04-22T00:00:00.0000000Z",
          "data_trade_start":"2017-07-14T00:00:00.0000000Z",
          "data_trade_end":"2025-04-19T00:00:00.0000000Z",
          "data_symbols_count":2992,
          "volume_1hrs_usd":23055328.11,
          "volume_1day_usd":12098309304.78,
          "volume_1mth_usd":505115715291.93,
          "rank":2,
          "integration_status":"INTEGRATED"
       }
    ]
    """.trimIndent()

    val exchangeUi = ExchangeUi(
        id = "BINANCE",
        name = "Binance",
        volume = "US$ 12,1 bi",
        originalVolume = 12098309304.78,
        color = Color.White // Not relevant for the stub, but needed for the constructor
    )

    fun expectedSortOptions(selectedField: SortOption.Field) =
        HomeUiState.generateSortOptions()
            .map { it.copy(isSelected = it.field == selectedField) }
}