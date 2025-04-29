package br.com.lucascordeiro.nexly.feature.details.presentation

import br.com.lucascordeiro.nexly.feature.details.presentation.model.ExchangeUi
import br.com.lucascordeiro.nexly.shared.ui.error.ErrorState
import io.github.lucascordeiro.ymir.core.state.UiState

internal data class DetailsUiState(
    val isLoading: Boolean = false,
    val exchange: ExchangeUi? = null,
    val error: ErrorState = ErrorState.Dismiss
) : UiState
