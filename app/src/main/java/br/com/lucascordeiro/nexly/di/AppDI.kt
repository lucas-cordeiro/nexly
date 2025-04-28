package br.com.lucascordeiro.nexly.di

import br.com.lucascordeiro.nexly.feature.details.di.FeatureDetailsDI
import br.com.lucascordeiro.nexly.feature.home.di.FeatureHomeDI
import br.com.lucascordeiro.nexly.shared.network.di.NetworkDI
import org.koin.core.module.Module

object AppDI {
    private val modules: List<Module> = mutableListOf<Module>().apply {
        addAll(NetworkDI.provideModules())
        addAll(FeatureHomeDI.provideModules())
        addAll(FeatureDetailsDI.provideModules())
    }

    fun provideModules(): List<Module> = modules
}