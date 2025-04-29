package br.com.lucascordeiro.nexly.shared.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.lucascordeiro.nexly.shared.ui.theme.Gray18
import br.com.lucascordeiro.nexly.shared.ui.theme.NexlyTheme

@Composable
internal fun UiTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(12.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = NexlyTheme.typography.title,
            fontWeight = FontWeight.Bold,
            color = Gray18
        )
    }
}