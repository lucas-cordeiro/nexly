package br.com.lucascordeiro.nexly.feature.home.domain.usecase

import br.com.lucascordeiro.nexly.feature.home.domain.model.Exchange

internal fun interface GetAllExchangesUseCase : suspend () -> List<Exchange>