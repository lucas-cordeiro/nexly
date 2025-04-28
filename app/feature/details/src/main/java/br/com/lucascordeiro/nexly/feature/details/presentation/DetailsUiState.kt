package br.com.lucascordeiro.nexly.feature.details.presentation

import br.com.lucascordeiro.nexly.feature.details.domain.model.Exchange
import io.github.lucascordeiro.ymir.core.state.UiState

internal data class DetailsUiState(
    val isLoading: Boolean = false,
    val exchange: Exchange? = null
) : UiState
