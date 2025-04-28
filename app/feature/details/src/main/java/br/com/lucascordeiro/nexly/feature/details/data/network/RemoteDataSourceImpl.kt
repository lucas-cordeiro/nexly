package br.com.lucascordeiro.nexly.feature.details.data.network

import br.com.lucascordeiro.nexly.feature.details.data.network.model.ExchangeResponse
import br.com.lucascordeiro.nexly.shared.network.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class RemoteDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteDataSource {
    private val path = "/v1/exchanges"

    override suspend fun getExchangeById(id: String): List<ExchangeResponse> {
        return httpClient().get("$path/$id").body()
    }
}