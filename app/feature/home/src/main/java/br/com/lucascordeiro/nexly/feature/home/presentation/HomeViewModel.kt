package br.com.lucascordeiro.nexly.feature.home.presentation

import androidx.lifecycle.viewModelScope
import br.com.lucascordeiro.nexly.feature.home.domain.usecase.GetAllExchangesUseCase
import br.com.lucascordeiro.nexly.feature.home.presentation.model.ExchangeUi
import br.com.lucascordeiro.nexly.feature.home.presentation.model.SortOption
import br.com.lucascordeiro.nexly.shared.network.error.NetworkException
import br.com.lucascordeiro.nexly.shared.network.error.NoInternetConnectionException
import br.com.lucascordeiro.nexly.shared.ui.error.ErrorState
import io.github.lucascordeiro.ymir.core.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class HomeViewModel(
    private val getAllExchangesUseCase: GetAllExchangesUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel<HomeUiState, HomeUiAction>(HomeUiState()) {
    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                setState { state -> state.copy(isLoading = true) }

                val exchanges = withContext(ioDispatcher) {
                    getAllExchangesUseCase().map { ExchangeUi.fromDomain(it) }
                }

                val field = SortOption.Field.Name
                val sortedExchanges = sortExchanges(field, exchanges)

                updateSortOptions(
                    field = field,
                    exchanges = sortedExchanges
                )

            } catch (e: Exception) {
                handleError(e)
            } finally {
                setState { state -> state.copy(isLoading = false) }
            }
        }
    }

    fun clickedExchange(exchange: ExchangeUi) {
        viewModelScope.launch {
            sendAction { HomeUiAction.NavigateToDetails(exchange.id) }
        }
    }

    fun clickedSortOption(option: SortOption) {
        if (option.isSelected) return

        updateSortOptions(
            field = option.field,
            exchanges = state.value.exchanges
        )
    }

    fun clickedError() {
        viewModelScope.launch {
            setState { state -> state.copy(error = ErrorState.Dismiss) }
            fetchData()
        }
    }

    private suspend fun handleError(e: Exception) {
        val error = when (e) {
            is NoInternetConnectionException -> ErrorState.Show.NoInternetConnection
            is NetworkException -> ErrorState.Show.Network
            else -> ErrorState.Show.Generic
        }

        setState { state -> state.copy(error = error, exchanges = emptyList()) }
    }

    private fun updateSortOptions(
        field: SortOption.Field,
        exchanges: List<ExchangeUi>
    ) {
        viewModelScope.launch {
            val options = state.value.sortOptions
            setState { state ->
                state.copy(
                    sortOptions = options.map { it.copy(isSelected = it.field == field) },
                    exchanges = sortExchanges(field, exchanges)
                )
            }

            sendAction { HomeUiAction.ScrollToTop }
        }
    }

    private fun sortExchanges(
        field: SortOption.Field,
        exchanges: List<ExchangeUi>
    ): List<ExchangeUi> {

        return when (field) {
            SortOption.Field.ID -> exchanges.sortedBy { it.id }
            SortOption.Field.Name -> exchanges.sortedBy { it.name }
            SortOption.Field.Volume -> exchanges.sortedByDescending { it.originalVolume }
        }
    }
}