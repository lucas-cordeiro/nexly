package br.com.lucascordeiro.nexly.shared.network.di

import br.com.lucascordeiro.nexly.shared.network.client.HttpClient
import br.com.lucascordeiro.nexly.shared.network.client.HttpClientImpl
import org.koin.core.module.Module
import org.koin.dsl.module

object NetworkDI {
    private val networkModule = module {
        single<HttpClient> { HttpClientImpl.default() }
    }

    fun provideModules(): List<Module> = listOf(networkModule)
}