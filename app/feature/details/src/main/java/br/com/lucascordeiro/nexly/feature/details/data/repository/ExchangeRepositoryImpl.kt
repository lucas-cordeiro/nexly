package br.com.lucascordeiro.nexly.feature.details.data.repository

import br.com.lucascordeiro.nexly.feature.details.data.network.RemoteDataSource
import br.com.lucascordeiro.nexly.feature.details.domain.model.Exchange
import br.com.lucascordeiro.nexly.feature.details.domain.repository.ExchangeRepository

internal class ExchangeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ExchangeRepository {
    override suspend fun getById(id: String): Exchange {
        return remoteDataSource.getExchangeById(id).first().toDomain()
    }
}