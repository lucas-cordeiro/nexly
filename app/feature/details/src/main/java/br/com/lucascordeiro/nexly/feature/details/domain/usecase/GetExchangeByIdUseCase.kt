package br.com.lucascordeiro.nexly.feature.details.domain.usecase

import br.com.lucascordeiro.nexly.feature.details.domain.model.Exchange


internal fun interface GetExchangeByIdUseCase : suspend (String) -> Exchange