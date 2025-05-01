package br.com.lucascordeiro.nexly.feature.home.presentation

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import br.com.lucascordeiro.nexly.feature.home.presentation.model.ExchangeUi
import br.com.lucascordeiro.nexly.shared.test.screen.ScreenRobot
import br.com.lucascordeiro.nexly.shared.ui.components.UITags

internal class HomeScreenRobot(
    private val composeTestRule: ComposeTestRule
) : ScreenRobot(composeTestRule) {

    fun isTitleDisplayed(): HomeScreenRobot {
        assertText(
            testTag = UITags.toolbarText,
            text = "Exchanges"
        )
        return this
    }

    fun isSortDisplayed(): HomeScreenRobot {
        assertText(
            testTag = HomeScreenTags.sortText,
            text = "Sort"
        )
        return this
    }

    fun isSortOptionDisplayed(text: String): HomeScreenRobot {
        composeTestRule.onAllNodes(hasTestTag(HomeScreenTags.sortOptionText))
            .filter(hasText(text))
            .onFirst()
            .assertExists()
            .assertIsDisplayed()
        return this
    }

    fun clickSortOption(text: String): HomeScreenRobot {
        composeTestRule.onAllNodes(hasTestTag(HomeScreenTags.sortOptionText))
            .filter(hasText(text))
            .onFirst()
            .performClick()
        return this
    }

    fun isExchangeDisplayed(exchangeUi: ExchangeUi): HomeScreenRobot {
        composeTestRule.onAllNodes(hasTestTag(HomeScreenTags.exchangeItem))
            .filter(
                hasText(exchangeUi.name) and hasClickAction()
            )
            .onFirst()
            .assert(hasText("ID: ${exchangeUi.id}"))
            .assert(hasText(exchangeUi.name))
            .assert(hasText("Volume: ${exchangeUi.volume}"))
        return this
    }

    fun clickExchange(exchangeUi: ExchangeUi): HomeScreenRobot {
        composeTestRule.onAllNodes(hasTestTag(HomeScreenTags.exchangeItem))
            .filter(
                hasText(exchangeUi.name) and hasClickAction()
            )
            .onFirst()
            .performClick()
        return this
    }

    fun isFeedbackDisplayed(text: String): HomeScreenRobot {
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