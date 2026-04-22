package com.example.myfirstclaudeapplication.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfirstclaudeapplication.ui.theme.MyFirstClaudeApplicationTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CounterScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private fun launchScreen() {
        composeRule.setContent {
            MyFirstClaudeApplicationTheme {
                CounterScreen()
            }
        }
    }

    @Test
    fun incrementButton_isDisplayed() {
        launchScreen()
        composeRule.onNodeWithText("Increment").assertIsDisplayed()
    }

    @Test
    fun counterText_showsZeroOnStart() {
        launchScreen()
        composeRule.onNodeWithText("Count: 0").assertIsDisplayed()
    }

    @Test
    fun clickingButton_incrementsCounterByOne() {
        launchScreen()
        composeRule.onNodeWithText("Increment").performClick()
        composeRule.onNodeWithText("Count: 1").assertIsDisplayed()
    }

    @Test
    fun clickingButtonMultipleTimes_accumulatesCount() {
        launchScreen()
        repeat(3) { composeRule.onNodeWithText("Increment").performClick() }
        composeRule.onNodeWithText("Count: 3").assertIsDisplayed()
    }

    @Test
    fun counterText_updatesAfterEachClick() {
        launchScreen()
        listOf("Count: 0", "Count: 1", "Count: 2").forEachIndexed { index, expected ->
            composeRule.onNodeWithText(expected).assertTextContains(expected)
            if (index < 2) composeRule.onNodeWithText("Increment").performClick()
        }
    }
}
