package br.com.lucascordeiro.nexly.feature.home.presentation.model

import androidx.compose.ui.graphics.Color
import br.com.lucascordeiro.nexly.feature.home.domain.model.Exchange
import br.com.lucascordeiro.nexly.shared.ui.theme.Blue01
import br.com.lucascordeiro.nexly.shared.ui.theme.Green01
import br.com.lucascordeiro.nexly.shared.ui.theme.Orange01
import br.com.lucascordeiro.nexly.shared.ui.theme.Red01
import br.com.lucascordeiro.nexly.shared.ui.utils.formatVolume
import java.util.Locale

internal data class ExchangeUi(
    val id: String,
    val name: String,
    val volume: String,
    val originalVolume: Double,
    val color: Color
) {
    companion object {
        fun fromDomain(exchange: Exchange): ExchangeUi {

            return ExchangeUi(
                id = exchange.id,
                name = exchange.name,
                volume = formatVolume(exchange.volume),
                originalVolume = exchange.volume,
                color = getRandomColor()
            )
        }



        private fun getRandomColor(): Color {
            val colors = listOf(
                Blue01,
                Green01,
                Orange01,
                Red01
            )
            return colors.random()
        }
    }
}
