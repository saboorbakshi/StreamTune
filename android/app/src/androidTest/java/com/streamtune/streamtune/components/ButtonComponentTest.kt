package com.streamtune.streamtune.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.streamtune.streamtune.ui.components.ButtonComponent
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ButtonComponentTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun onClick_is_called() {
        // Define the text to be displayed
        val labelText = "Click me"

        // default: false
        var clicked = false;

        rule.setContent {
            ButtonComponent(labelText){
                clicked = true
            }
        }

        // Perform a click action on the button
        rule.onNodeWithText(labelText).performClick()

        // Verify that onClick is called
        assertTrue(clicked)
    }
}