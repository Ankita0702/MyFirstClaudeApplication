package com.example.myfirstclaudeapplication.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
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

    // --- Initial state ---

    @Test
    fun counterText_showsZeroOnStart() {
        launchScreen()
        composeRule.onNodeWithText("Count: 0").assertIsDisplayed()
    }

    @Test
    fun plusButton_isDisplayed() {
        launchScreen()
        composeRule.onNodeWithText("+").assertIsDisplayed()
    }

    @Test
    fun minusButton_isDisplayed() {
        launchScreen()
        composeRule.onNodeWithText("-").assertIsDisplayed()
    }

    @Test
    fun minusButton_isDisabledAtZero() {
        launchScreen()
        composeRule.onNodeWithText("-").assertIsNotEnabled()
    }

    @Test
    fun plusButton_isEnabledAtZero() {
        launchScreen()
        composeRule.onNodeWithText("+").assertIsEnabled()
    }

    // --- Increment ---

    @Test
    fun clickingPlus_incrementsCounterByOne() {
        launchScreen()
        composeRule.onNodeWithText("+").performClick()
        composeRule.onNodeWithText("Count: 1").assertIsDisplayed()
    }

    @Test
    fun clickingPlusMultipleTimes_accumulatesCount() {
        launchScreen()
        repeat(3) { composeRule.onNodeWithText("+").performClick() }
        composeRule.onNodeWithText("Count: 3").assertIsDisplayed()
    }

    // --- Decrement ---

    @Test
    fun clickingMinus_decrementsCounterByOne() {
        launchScreen()
        composeRule.onNodeWithText("+").performClick()
        composeRule.onNodeWithText("+").performClick()
        composeRule.onNodeWithText("-").performClick()
        composeRule.onNodeWithText("Count: 1").assertIsDisplayed()
    }

    @Test
    fun clickingMinusMultipleTimes_decrementsCorrectly() {
        launchScreen()
        repeat(5) { composeRule.onNodeWithText("+").performClick() }
        repeat(3) { composeRule.onNodeWithText("-").performClick() }
        composeRule.onNodeWithText("Count: 2").assertIsDisplayed()
    }

    // --- Boundary: counter cannot go below 0 ---

    @Test
    fun minusButton_isDisabledWhenCounterIsZero() {
        launchScreen()
        composeRule.onNodeWithText("-").assertIsNotEnabled()
    }

    @Test
    fun minusButton_becomesEnabledAfterIncrement() {
        launchScreen()
        composeRule.onNodeWithText("+").performClick()
        composeRule.onNodeWithText("-").assertIsEnabled()
    }

    @Test
    fun minusButton_becomesDisabledWhenCounterReturnsToZero() {
        launchScreen()
        composeRule.onNodeWithText("+").performClick()
        composeRule.onNodeWithText("-").performClick()
        composeRule.onNodeWithText("-").assertIsNotEnabled()
    }

    @Test
    fun counterNeverGoesBelowZero_clickingDisabledMinus() {
        launchScreen()
        // Minus is disabled at 0; clicking it should have no effect
        composeRule.onNodeWithText("-").performClick()
        composeRule.onNodeWithText("Count: 0").assertIsDisplayed()
    }

    @Test
    fun incrementThenDecrementToZero_showsZero() {
        launchScreen()
        repeat(3) { composeRule.onNodeWithText("+").performClick() }
        repeat(3) { composeRule.onNodeWithText("-").performClick() }
        composeRule.onNodeWithText("Count: 0").assertIsDisplayed()
    }
}