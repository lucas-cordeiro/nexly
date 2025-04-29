package br.com.lucascordeiro.nexly.feature.details.presentation

import androidx.lifecycle.viewModelScope
import br.com.lucascordeiro.nexly.feature.details.domain.usecase.GetExchangeByIdUseCase
import br.com.lucascordeiro.nexly.feature.details.presentation.model.ExchangeUi
import io.github.lucascordeiro.ymir.core.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class DetailsViewModel(
    private val exchangeId: String,
    private val getExchangeByIdUseCase: GetExchangeByIdUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel<DetailsUiState, DetailsUiAction>(DetailsUiState()) {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                setState { state -> state.copy(isLoading = true) }

                val exchange = withContext(ioDispatcher) {
                    getExchangeByIdUseCase(exchangeId)
                }

                setState { state -> state.copy(exchange = ExchangeUi.fromDomain(exchange)) }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                setState { state -> state.copy(isLoading = false) }
            }
        }
    }

    fun clickedBack() {
        viewModelScope.launch {
            sendAction { DetailsUiAction.NavigateBack }
        }
    }

    fun clickedWebsite() {
        viewModelScope.launch {
            val website = state.value.exchange?.website ?: return@launch
            sendAction { DetailsUiAction.OpenWebsite(website) }
        }
    }
}