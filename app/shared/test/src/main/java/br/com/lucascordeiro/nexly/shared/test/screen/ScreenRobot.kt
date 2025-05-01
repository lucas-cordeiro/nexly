package br.com.lucascordeiro.nexly.shared.test.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

abstract class ScreenRobot(
    private val composeTestRule: ComposeTestRule
) {
    fun assertDisplayed(text: String) : ScreenRobot {
        composeTestRule
            .onNodeWithText(text)
            .assertExists()
            .assertIsDisplayed()
        return this
    }

    fun assertText(testTag: String, text: String, useUnmergedTree: Boolean = false) : ScreenRobot {
        composeTestRule
            .onNodeWithTag(testTag, useUnmergedTree)
            .assertTextEquals(text)
            .assertIsDisplayed()

        return this
    }

    fun performClick(text: String) : ScreenRobot {
        assertDisplayed(text)

        composeTestRule
            .onNodeWithText(text)
            .performClick()

        return this
    }
}