package com.streamtune.streamtune.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.streamtune.streamtune.ui.components.PasswordTextFieldComponent
import com.streamtune.streamtune.ui.components.SearchTextFieldComponent
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class SearchTextFieldComponentTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun searchValue_sameAs_testInput() {
        // Define the text to be displayed
        val labelText = "Search"

        var inputValue = ""

        // Set up the UI with the HeadingTextComponent
        rule.setContent {
            SearchTextFieldComponent(labelText) {
                inputValue = it
            }
        }

        // Enter text into the text field
        val inputText = "https://example.com"
        rule.onNodeWithText(labelText).performTextInput(inputText)

        // Verify that the entered text matches the test input
        Assert.assertEquals(inputValue, inputText)
    }

    @Test
    fun checkSearchIcon() {
        val labelText = "Search"

        // Render the composable
        rule.setContent {
            SearchTextFieldComponent(labelText) {}
        }

        // Verify that the trailing icon is displayed
        rule.onNodeWithContentDescription("Search").assertExists()
    }
}