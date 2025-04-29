package br.com.lucascordeiro.nexly.feature.details.domain.model

import java.util.Date

data class ActivityPeriods(
    val quoteStart: Date?,
    val quoteEnd: Date?,
    val orderbookStart: Date?,
    val orderbookEnd: Date?,
    val tradeStart: Date?,
    val tradeEnd: Date?
)
