package br.com.lucascordeiro.nexly.feature.home.presentation

import androidx.lifecycle.viewModelScope
import br.com.lucascordeiro.nexly.feature.home.domain.model.Exchange
import br.com.lucascordeiro.nexly.feature.home.domain.usecase.GetAllExchangesUseCase
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
                    getAllExchangesUseCase()
                }

                setState { state -> state.copy(exchanges = exchanges) }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                setState { state -> state.copy(isLoading = false) }
            }
        }
    }

    fun clickedExchange(exchange: Exchange) {
        viewModelScope.launch {
            sendAction { HomeUiAction.NavigateToDetails(exchange.id) }
        }
    }
}