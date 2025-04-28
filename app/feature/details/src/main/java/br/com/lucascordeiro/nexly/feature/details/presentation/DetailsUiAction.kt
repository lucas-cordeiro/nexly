package br.com.lucascordeiro.nexly.feature.details.presentation

import io.github.lucascordeiro.ymir.core.action.UiAction

internal sealed class DetailsUiAction : UiAction {
    data object NavigateBack : DetailsUiAction()
}