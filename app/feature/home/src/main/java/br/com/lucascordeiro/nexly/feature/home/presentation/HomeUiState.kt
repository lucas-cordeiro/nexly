package br.com.lucascordeiro.nexly.feature.home.presentation

import br.com.lucascordeiro.nexly.feature.home.domain.model.Exchange
import io.github.lucascordeiro.ymir.core.state.UiState

internal data class HomeUiState(
    val isLoading: Boolean = false,
    val exchanges: List<Exchange> = emptyList(),
) : UiState
