package com.akjaw.testing.compose.pager

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipe
import androidx.compose.ui.test.swipeLeft
import com.akjaw.testing.compose.pager.ui.theme.TestingComposePagerTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StaticPagerScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TestingComposePagerTheme {
                StaticPagerScreen()
            }
        }
    }

    @Test
    fun clickingGoToInfoOpensTheInfoTab() {
        composeTestRule.onPagerButton(page = StaticPagerScreenPage.Summary, text = "Go to info")
            .performClick()

        composeTestRule.onNodeWithText("Info").assertIsSelected()
        composeTestRule.onNodeWithText("The Information page").assertIsDisplayed()
    }

    @Test
    fun swipingTwoTimesLeftOpensTheDetailsTab() {
        composeTestRule.onRoot().performTouchInput {
            swipeLeft()
            swipeLeft()
        }

        composeTestRule.onNodeWithText("Details").assertIsSelected()
        composeTestRule.onNodeWithText("The Details page").assertIsDisplayed()
    }

    private fun ComposeContentTestRule.onPagerButton(
        page: StaticPagerScreenPage,
        text: String
    ): SemanticsNodeInteraction {
        val isOnTheCorrectPage =
            hasAnyAncestor(hasTestTag(TestTagsStaticPagerScreen.getPageTag(page)))
        return onAllNodesWithText(text)
            .filterToOne(isOnTheCorrectPage)
    }
}
