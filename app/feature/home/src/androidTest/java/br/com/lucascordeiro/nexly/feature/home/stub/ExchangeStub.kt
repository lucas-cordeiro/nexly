package br.com.lucascordeiro.nexly.feature.home.stub

import br.com.lucascordeiro.nexly.feature.home.presentation.model.ExchangeUi
import br.com.lucascordeiro.nexly.shared.ui.theme.Blue01

internal object ExchangeStub {
    val exchangeUi = ExchangeUi(
        id = "BINANCE",
        name = "Binance",
        volume = "US$ 12.1 bi",
        originalVolume = 12098309304.78,
        color = Blue01
    )
}