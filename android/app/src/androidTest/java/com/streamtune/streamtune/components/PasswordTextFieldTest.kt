package com.streamtune.streamtune.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.streamtune.streamtune.ui.components.PasswordTextFieldComponent
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun label_is_displayed() {
        // Define the text to be displayed
        val labelText = "Password"

        // Set up the UI with the PasswordTextFieldComponent
        rule.setContent {
            PasswordTextFieldComponent(labelText) {}
        }

        // Verify that the text is displayed
        rule.onNodeWithText(labelText).assertExists()
    }

    @Test
    fun passwordValue_sameAs_testInput() {
        // Define the text to be displayed
        val labelText = "Password"

        var inputValue = ""

        // Set up the UI with the HeadingTextComponent
        rule.setContent {
            PasswordTextFieldComponent(labelText) {
                inputValue = it
            }
        }

        // Enter text into the text field
        val inputText = "Example123"
        rule.onNodeWithText(labelText).performTextInput(inputText)

        // Verify that the entered text matches the test input
        assertEquals(inputValue, inputText)
    }

    @Test
    fun password_is_hidden() {
        // Define the text to be displayed
        val labelText = "Password"

        var inputValue = ""

        // Set up the UI with the HeadingTextComponent
        rule.setContent {
            PasswordTextFieldComponent(labelText) {
                inputValue = it
            }
        }

        // Enter text into the text field
        val inputText = "Example123"
        rule.onNodeWithText(labelText).performTextInput(inputText)

        // Verify that the entered text is hidden (Default Visibility: false)
        rule.onNodeWithText(inputText).assertDoesNotExist()
    }

    @Test
    fun password_is_visible() {
        // Define the text to be displayed
        val labelText = "Password"

        var inputValue = ""

        // Set up the UI with the HeadingTextComponent
        rule.setContent {
            PasswordTextFieldComponent(labelText) {
                inputValue = it
            }
        }

        // Click the visibility icon
        rule.onNodeWithContentDescription("Password Visibility").performClick()

        // Enter text into the text field
        val inputText = "Example123"
        rule.onNodeWithText(labelText).performTextInput(inputText)

        // Verify that the entered text is visible
        rule.onNodeWithText(inputText).assertExists()
    }
}