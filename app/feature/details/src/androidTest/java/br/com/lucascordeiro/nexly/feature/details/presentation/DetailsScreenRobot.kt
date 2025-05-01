package br.com.lucascordeiro.nexly.feature.details.presentation

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.lucascordeiro.nexly.feature.details.presentation.model.ExchangeVolumeUi
import br.com.lucascordeiro.nexly.shared.test.screen.ScreenRobot
import br.com.lucascordeiro.nexly.shared.ui.components.UITags

internal class DetailsScreenRobot(
    private val composeTestRule: ComposeTestRule
) : ScreenRobot(composeTestRule) {

    fun isTitleDisplayed(text: String): DetailsScreenRobot {
        assertText(
            testTag = UITags.toolbarText,
            text = text
        )
        return this
    }

    fun isExchangeIDDisplayed(exchangeId: String): DetailsScreenRobot {
        assertText(
            DetailsScreenTags.exchangeId,
            "ID: $exchangeId"
        )

        return this
    }

    fun isExchangeVolumeDisplayed(exchangeVolumeUi: ExchangeVolumeUi): DetailsScreenRobot {

        assertDisplayed(exchangeVolumeUi.lastHour)
        assertDisplayed(exchangeVolumeUi.lastDay)
        assertDisplayed(exchangeVolumeUi.lastMonth)

        return this
    }

    fun clickWebsite() {
        performClick("Open")
    }

    fun isFeedbackDisplayed(text: String): DetailsScreenRobot {
        composeTestRule
            .onNodeWithTag(UITags.feedback)
            .isDisplayed()

        assertText(
            testTag = UITags.feedbackTitleText,
            text = "Ops"
        )
        assertText(
            testTag = UITags.feedbackMessageText,
            text = text
        )
        assertText(
            testTag = UITags.feedbackButton,
            text = "Retry"
        )

        return this
    }

}