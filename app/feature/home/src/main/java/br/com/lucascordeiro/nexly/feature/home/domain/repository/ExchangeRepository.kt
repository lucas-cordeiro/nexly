package br.com.lucascordeiro.nexly.feature.home.domain.repository

import br.com.lucascordeiro.nexly.feature.home.domain.model.Exchange

internal interface ExchangeRepository {
    suspend fun getAll(): List<Exchange>
}