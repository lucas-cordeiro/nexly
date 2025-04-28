package br.com.lucascordeiro.nexly.feature.home.data.network.model

import br.com.lucascordeiro.nexly.feature.home.domain.model.Exchange
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ExchangeResponse(
    @SerialName("exchange_id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("volume_1day_usd")
    val volume: Double? = null
) {
    fun toDomain(): Exchange {
        return Exchange(
            id = id.orEmpty(),
            name = name.orEmpty(),
            volume = volume ?: 0.0
        )
    }
}