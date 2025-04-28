package br.com.lucascordeiro.nexly.feature.home.di

import br.com.lucascordeiro.nexly.feature.home.data.network.RemoteDataSource
import br.com.lucascordeiro.nexly.feature.home.data.network.RemoteDataSourceImpl
import br.com.lucascordeiro.nexly.feature.home.data.repository.ExchangeRepositoryImpl
import br.com.lucascordeiro.nexly.feature.home.domain.repository.ExchangeRepository
import br.com.lucascordeiro.nexly.feature.home.domain.usecase.GetAllExchangesUseCase
import br.com.lucascordeiro.nexly.feature.home.presentation.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object FeatureHomeDI {
    private val viewModelsModule = module {
        viewModel {
            val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl(httpClient = get())
            val repository: ExchangeRepository =
                ExchangeRepositoryImpl(remoteDataSource = remoteDataSource)
            val getAllExchangesUseCase = GetAllExchangesUseCase(repository::getAll)

            HomeViewModel(getAllExchangesUseCase = getAllExchangesUseCase)
        }
    }

    fun provideModules(): List<Module> = listOf(viewModelsModule)
}