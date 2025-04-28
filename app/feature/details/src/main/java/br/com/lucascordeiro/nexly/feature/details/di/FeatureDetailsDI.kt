package br.com.lucascordeiro.nexly.feature.details.di

import br.com.lucascordeiro.nexly.feature.details.data.network.RemoteDataSource
import br.com.lucascordeiro.nexly.feature.details.data.network.RemoteDataSourceImpl
import br.com.lucascordeiro.nexly.feature.details.data.repository.ExchangeRepositoryImpl
import br.com.lucascordeiro.nexly.feature.details.domain.repository.ExchangeRepository
import br.com.lucascordeiro.nexly.feature.details.domain.usecase.GetExchangeByIdUseCase
import br.com.lucascordeiro.nexly.feature.details.presentation.DetailsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object FeatureDetailsDI {
    private val viewModelsModule = module {
        viewModel { (exchangeId: String) ->
            val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl(httpClient = get())
            val repository: ExchangeRepository = ExchangeRepositoryImpl(remoteDataSource = remoteDataSource)
            val getExchangeByIdUseCase = GetExchangeByIdUseCase(repository::getById)

            DetailsViewModel(
                exchangeId = exchangeId,
                getExchangeByIdUseCase = getExchangeByIdUseCase
            )
        }
    }

    fun provideModules(): List<Module> = listOf(viewModelsModule)
}