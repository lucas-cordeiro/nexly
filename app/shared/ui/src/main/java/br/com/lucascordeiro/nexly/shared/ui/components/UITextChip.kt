package br.com.lucascordeiro.nexly.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import br.com.lucascordeiro.nexly.shared.ui.theme.NexlyTheme

@Composable
fun UITextChip(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    style: TextStyle = NexlyTheme.typography.body,
) {
    Text(
        text = text,
        style = style,
        color = color,
        modifier = modifier
            .background(
                color = color.copy(.075f),
                shape = MaterialTheme.shapes.small
            )
            .padding(
                horizontal = 12.dp,
                vertical = 4.dp
            )
    )

}