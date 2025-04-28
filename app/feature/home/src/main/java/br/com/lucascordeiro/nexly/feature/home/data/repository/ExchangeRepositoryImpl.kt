package br.com.lucascordeiro.nexly.feature.home.data.repository

import br.com.lucascordeiro.nexly.feature.home.data.network.RemoteDataSource
import br.com.lucascordeiro.nexly.feature.home.domain.model.Exchange
import br.com.lucascordeiro.nexly.feature.home.domain.repository.ExchangeRepository

internal class ExchangeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ExchangeRepository {
    override suspend fun getAll(): List<Exchange> {
        return remoteDataSource.getExchanges()
            .filter { exchangeResponse ->
                !exchangeResponse.id.isNullOrBlank() &&
                        !exchangeResponse.name.isNullOrBlank()
            }
            .map { it.toDomain() }
    }
}