package br.com.lucascordeiro.nexly.feature.details.presentation

import androidx.lifecycle.viewModelScope
import br.com.lucascordeiro.nexly.feature.details.domain.usecase.GetExchangeByIdUseCase
import br.com.lucascordeiro.nexly.feature.details.presentation.model.ExchangeUi
import br.com.lucascordeiro.nexly.shared.network.error.NetworkException
import br.com.lucascordeiro.nexly.shared.network.error.NoInternetConnectionException
import br.com.lucascordeiro.nexly.shared.ui.error.ErrorState
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
                handleError(e)
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

        setState { state -> state.copy(error = error, exchange = null) }
    }

    fun clickedWebsite() {
        viewModelScope.launch {
            val website = state.value.exchange?.website ?: return@launch
            sendAction { DetailsUiAction.OpenWebsite(website) }
        }
    }
}