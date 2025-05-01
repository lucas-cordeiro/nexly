package br.com.lucascordeiro.nexly.shared.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.lucascordeiro.nexly.shared.ui.error.ErrorState
import br.com.lucascordeiro.nexly.shared.ui.theme.NexlyTheme
import br.com.lucascordeiro.nexly.shared.ui.theme.Red01

@Composable
fun UiFeedback(
    errorState: ErrorState,
    onDismissRequest: () -> Unit
) {
    if(errorState is ErrorState.Show) {
        UiFeedback(
            message = errorState.message,
            onDismissRequest = onDismissRequest
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiFeedback(
    message: String,
    onDismissRequest: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.testTag(UITags.feedback)
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .defaultMinSize(minHeight = 160.dp)
                    .height(IntrinsicSize.Min)
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                UITextChip(
                    text ="Ops",
                    style = NexlyTheme.typography.title,
                    color = Red01,
                    modifier = Modifier
                        .testTag(UITags.feedbackTitleText)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = message,
                    style = NexlyTheme.typography.caption,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .testTag(UITags.feedbackMessageText)
                )

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(minHeight = 16.dp)
                )

                UiTextButton(
                    text = "Retry",
                    onClick = onDismissRequest,
                    modifier = Modifier
                        .testTag(UITags.feedbackButton)
                )
            }
        }
    }
}