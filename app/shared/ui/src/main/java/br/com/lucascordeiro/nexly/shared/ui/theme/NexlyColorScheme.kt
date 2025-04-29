package br.com.lucascordeiro.nexly.shared.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class NexlyColorScheme(
    val primary: Color = Primary,
    val secondary: Color = Secondary,
    val background: Color = Background,
    val surface: Color = Color.White
)
