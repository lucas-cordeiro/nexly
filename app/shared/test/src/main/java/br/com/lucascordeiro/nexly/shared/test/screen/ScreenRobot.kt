package br.com.lucascordeiro.nexly.shared.test.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput

abstract class ScreenRobot(
    private val composeTestRule: ComposeTestRule
) {
    fun assertDisplayed(testTag: String, useUnmergedTree: Boolean = false) : ScreenRobot {
        composeTestRule
            .onNodeWithTag(testTag, useUnmergedTree)
            .assertExists()
            .assertIsDisplayed()
        return this
    }

    fun assertNotExists(testTag: String, useUnmergedTree: Boolean = false) : ScreenRobot {
        composeTestRule
            .onNodeWithTag(testTag, useUnmergedTree)
            .assertDoesNotExist()
        return this
    }

    fun assertText(testTag: String, text: String, useUnmergedTree: Boolean = false) : ScreenRobot {
        composeTestRule
            .onNodeWithTag(testTag, useUnmergedTree)
            .assertTextEquals(text)
            .assertIsDisplayed()

        return this
    }

    fun inputText(testTag: String, text: String, useUnmergedTree: Boolean = false) : ScreenRobot {
        composeTestRule
            .onNodeWithTag(testTag, useUnmergedTree)
            .performTextInput(text)
        return this
    }
}