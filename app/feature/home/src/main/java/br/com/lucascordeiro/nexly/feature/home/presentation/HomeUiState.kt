package br.com.lucascordeiro.nexly.feature.home.presentation

import br.com.lucascordeiro.nexly.feature.home.presentation.model.ExchangeUi
import br.com.lucascordeiro.nexly.feature.home.presentation.model.SortOption
import br.com.lucascordeiro.nexly.shared.ui.error.ErrorState
import io.github.lucascordeiro.ymir.core.state.UiState

internal data class HomeUiState(
    val isLoading: Boolean = false,
    val exchanges: List<ExchangeUi> = emptyList(),
    val sortOptions: List<SortOption> = generateSortOptions(),
    val error: ErrorState = ErrorState.Dismiss
) : UiState {
    companion object {
        fun generateSortOptions(): List<SortOption> {
            return listOf(
                SortOption(SortOption.Field.Name),
                SortOption(SortOption.Field.ID),
                SortOption(SortOption.Field.Volume)
            )
        }
    }
}
