package br.com.lucascordeiro.nexly.feature.home.presentation

import io.github.lucascordeiro.ymir.core.action.UiAction

internal sealed class HomeUiAction : UiAction {
    data class NavigateToDetails(val id: String) : HomeUiAction()
    data object ScrollToTop : HomeUiAction()
}