package br.com.lucascordeiro.nexly.feature.details.data.network

import br.com.lucascordeiro.nexly.feature.details.data.network.model.ExchangeResponse


internal interface RemoteDataSource {
    suspend fun getExchangeById(id: String): List<ExchangeResponse>
}