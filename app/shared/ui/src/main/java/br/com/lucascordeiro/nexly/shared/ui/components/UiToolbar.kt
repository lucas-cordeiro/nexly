package br.com.lucascordeiro.nexly.shared.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import br.com.lucascordeiro.nexly.shared.ui.R
import br.com.lucascordeiro.nexly.shared.ui.theme.Gray03
import br.com.lucascordeiro.nexly.shared.ui.theme.Gray08
import br.com.lucascordeiro.nexly.shared.ui.theme.NexlyTheme

@Composable
fun UiToolbar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(NexlyTheme.colorScheme.background)
            .fillMaxWidth()
            .height(48.dp)
    ) {

        Spacer(modifier = Modifier.width(22.dp))

        this.AnimatedVisibility(
            visible = onBackClick != null,
            enter = expandHorizontally(expandFrom = Alignment.Start) + fadeIn(),
            exit = shrinkHorizontally(shrinkTowards = Alignment.Start) + fadeOut()
        ) {
           Row {
               BackButton(onClick = { onBackClick?.invoke() })
               Spacer(modifier = Modifier.width(16.dp))
           }
        }

        Text(
            text = title,
            style = NexlyTheme.typography.largeTitle
        )
    }
}

@Composable
private fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Gray03.copy(alpha = .2f),
            contentColor = Gray08
        ),
        onClick = onClick,
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
            contentDescription = "Botão de voltar"
        )
    }
}
