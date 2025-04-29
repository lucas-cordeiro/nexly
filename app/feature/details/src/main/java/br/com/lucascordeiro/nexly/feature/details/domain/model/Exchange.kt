package br.com.lucascordeiro.nexly.feature.details.domain.model

internal data class Exchange(
    val id: String,
    val name: String,
    val volume: ExchangeVolume,
    val rank: Double,
    val activityPeriods: ActivityPeriods,
    val website: String
)