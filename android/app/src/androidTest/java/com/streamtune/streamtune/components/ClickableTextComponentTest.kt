package com.streamtune.streamtune.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.streamtune.streamtune.ui.components.ClickableTextComponent
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ClickableTextComponentTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun displayText() {
        val msg = "Click me"

        rule.setContent {
            ClickableTextComponent(msg) {}
        }

        // Verify text is displayed correctly
        rule.onNodeWithText(msg).assertExists()
    }

    @Test
    fun clickText(){
        val msg = "Click me"
        var clicked = false;
        val clickText: () -> Unit = { clicked = true }

        rule.setContent {
            ClickableTextComponent(msg, clickText)
        }

        // Perform a click action on the button
        rule.onNodeWithText(msg).performClick()

        // Verify that onClick is called
        assertTrue(clicked)
    }

}