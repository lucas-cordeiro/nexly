package br.com.lucascordeiro.nexly.shared.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

object NexlyTheme {
    val colorScheme: NexlyColorScheme
        @Composable
        get() = LocalNexlyColorScheme.current

    /**
     * Typography for the theme.
     */
    val typography: NexlyTypography
        @Composable
        get() = LocalNexlyTypography.current
}

@Composable
fun UiNexlyTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = NexlyColorScheme()
    val typography = NexlyTypography.NexlyTypographyDefault

    CompositionLocalProvider(
        LocalNexlyColorScheme provides colorScheme,
        LocalNexlyTypography provides typography
    ) {
        MaterialTheme(
            colorScheme = lightColorScheme(
                primary = colorScheme.primary,
                onPrimaryContainer = colorScheme.primary.copy(alpha = .8f),
                primaryContainer = colorScheme.primary.copy(alpha = .2f),
                secondary = colorScheme.secondary,
                onSecondaryContainer = colorScheme.secondary.copy(alpha = .8f),
                secondaryContainer = colorScheme.secondary.copy(alpha = .2f),
                surface = colorScheme.surface,
                background = colorScheme.background
            ),
            typography = typography.toMaterialTypography(),
            content = content
        )
    }

}

val LocalNexlyColorScheme = staticCompositionLocalOf {
    NexlyColorScheme()
}

val LocalNexlyTypography = staticCompositionLocalOf {
    NexlyTypography()
}
