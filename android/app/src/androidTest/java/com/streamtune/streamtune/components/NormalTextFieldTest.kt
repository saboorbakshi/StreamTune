package com.streamtune.streamtune.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.streamtune.streamtune.ui.components.NormalTextFieldComponent
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


class NormalTextFieldTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun label_is_displayed() {
        // Define the text to be displayed
        val labelText = "Email"

        // Set up the UI with the NormalTextFieldComponent
        rule.setContent {
            NormalTextFieldComponent(labelText) {}
        }

        // Verify that the text is displayed
        rule.onNodeWithText(labelText).assertExists()
    }

    @Test
    fun input_is_displayed() {
        // Define the text to be displayed
        val labelText = "Email"

        var inputValue = ""

        // Set up the UI with the HeadingTextComponent
        rule.setContent {
            NormalTextFieldComponent(labelText) {
                inputValue = it
            }
        }

        // Enter text into the text field
        val inputText = "test@example.com"
        rule.onNodeWithText(labelText).performTextInput(inputText)

        // Verify that the entered text is displayed
        rule.onNodeWithText(inputText).assertExists()
    }

    @Test
    fun inputValue_sameAs_testInput() {
        // Define the text to be displayed
        val labelText = "Email"

        var inputValue = ""

        // Set up the UI with the HeadingTextComponent
        rule.setContent {
            NormalTextFieldComponent(labelText) {
                inputValue = it
            }
        }

        // Enter text into the text field
        val inputText = "test@example.com"
        rule.onNodeWithText(labelText).performTextInput(inputText)

        // Verify that the entered text matches the test input
        assertEquals(inputValue, inputText)

    }
}