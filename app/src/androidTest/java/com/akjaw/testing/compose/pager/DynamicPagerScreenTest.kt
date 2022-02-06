package com.akjaw.testing.compose.pager

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performGesture
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import com.akjaw.testing.compose.pager.ui.theme.TestingComposePagerTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DynamicPagerScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TestingComposePagerTheme {
                DynamicPagerScreen()
            }
        }
    }

    @Test
    fun increaseClickedThenTabCountIncreases() {
        composeTestRule.onPagerButton(pageIndex = 0, text = "Increase").performClick()
        composeTestRule.onPagerButton(pageIndex = 0, text = "Increase").performClick()
        composeTestRule.onPagerButton(pageIndex = 0, text = "Increase").performClick()

        composeTestRule.onNodeWithTag(TestTagsDynamicPagerScreen.tabRow)
            .performTouchInput {
                swipeLeft()
            }
        composeTestRule.onNodeWithText("Page 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Page 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Page 3").assertIsDisplayed()
    }

    @Test
    fun secondTabClickedThenTextIsCorrect() {
        composeTestRule.onPagerButton(pageIndex = 0, text = "Increase").performClick()

        composeTestRule.onNodeWithText("Page 1").performClick()

        composeTestRule.onNodeWithText("On page: 1").assertIsDisplayed()
    }

    @Test
    fun increaseOnSecondTabClickedThenTabCountIncreases() {
        composeTestRule.onPagerButton(pageIndex = 0, text = "Increase").performClick()
        composeTestRule.onNodeWithText("Page 1").performClick()

        composeTestRule.onPagerButton(pageIndex = 1, text = "Increase").performClick()

        composeTestRule.onNodeWithText("Page 2").assertIsDisplayed()
    }

    private fun ComposeContentTestRule.onPagerButton(
        pageIndex: Int,
        text: String
    ): SemanticsNodeInteraction {
        val isOnTheCorrectPage =
            hasAnyAncestor(hasTestTag(TestTagsDynamicPagerScreen.getPageTag(pageIndex)))
        return onAllNodesWithText(text)
            .filterToOne(isOnTheCorrectPage)
    }
}