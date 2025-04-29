package br.com.lucascordeiro.nexly.shared.ui.error

sealed class ErrorState {
    data object Dismiss: ErrorState()

    sealed class Show(val message: String): ErrorState() {
        data object Generic: Show("An error occurred")
        data object Network: Show("Network error")
        data object NoInternetConnection: Show("No internet connection")
    }
}