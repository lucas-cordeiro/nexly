package br.com.lucascordeiro.nexly.feature.details.domain.repository

import br.com.lucascordeiro.nexly.feature.details.domain.model.Exchange


internal interface ExchangeRepository {
    suspend fun getById(id: String): Exchange
}