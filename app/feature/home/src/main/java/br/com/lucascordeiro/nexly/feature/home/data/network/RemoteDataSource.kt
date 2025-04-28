package br.com.lucascordeiro.nexly.feature.home.data.network

import br.com.lucascordeiro.nexly.feature.home.data.network.model.ExchangeResponse

internal interface RemoteDataSource {
    suspend fun getExchanges(): List<ExchangeResponse>
}