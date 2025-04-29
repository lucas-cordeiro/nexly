package br.com.lucascordeiro.nexly.shared.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.lucascordeiro.nexly.shared.ui.theme.Gray0
import br.com.lucascordeiro.nexly.shared.ui.theme.Secondary

@Composable
fun UiLoading(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = isVisible,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CircularProgressIndicator(
                    color = Secondary,
                    modifier = Modifier
                        .shadow(
                            elevation = 12.dp,
                            spotColor = Color(0X212E381F),
                            shape = CircleShape
                        )
                        .background(
                            color = Gray0,
                            shape = CircleShape
                        )
                        .padding(6.dp)
                )
            }
        }
    }
}