package com.streamtune.streamtune.components

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.streamtune.streamtune.ui.components.HeadingTextComponent
import org.junit.Rule
import org.junit.Test

class HeadingTextComponentTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun headingText_is_displayed() {
        // Define the text to be displayed
        val labelText = "Heading Text"

        // Set up the UI with the HeadingTextComponent
        rule.setContent {
            HeadingTextComponent(labelText)
        }

        // Verify that the text is displayed
        rule.onNodeWithText(labelText).assertExists()
    }

    @Test
    fun headingText_is_correct() {
        // Define the text to be displayed
        val labelText = "Heading Text"

        // Set up the UI with the HeadingTextComponent
        rule.setContent {
            HeadingTextComponent(labelText)
        }

        // Verify that the text is correct
        rule.onNodeWithText(labelText).assertTextEquals(labelText)
    }
}