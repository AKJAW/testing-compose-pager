package com.akjaw.testing.compose.pager

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
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
    fun theSecondPageIsInTheNodeTree() {
        composeTestRule.onNodeWithText("The Summary page").assertIsDisplayed()
        composeTestRule.onNodeWithText("The Information page").assertIsNotDisplayed()
    }

    @Test
    fun theThirdPageDoesNotExistInTheNodeTree() {
        composeTestRule.onNodeWithText("The Details page").assertDoesNotExist()
    }

    @Test
    fun clickingGoToInfoOpensTheInfoPage() {
        composeTestRule.onNodeWithText("Go to info").performClick()

        composeTestRule.onNodeWithText("Info").assertIsSelected()
        composeTestRule.onNodeWithText("The Information page").assertIsDisplayed()
    }

    @Test
    fun clickingInfoTabOpensTheInfoPage() {
        composeTestRule.onNodeWithText("Info").performClick()

        composeTestRule.onNodeWithText("Info").assertIsSelected()
        composeTestRule.onNodeWithText("The Information page").assertIsDisplayed()
    }

    @Test
    fun swipingLeftOnRootTwoTimesOpensTheDetailsPage() {
        composeTestRule.onRoot().performTouchInput {
            swipeLeft()
            swipeLeft()
        }

        composeTestRule.onNodeWithText("Details").assertIsSelected()
        composeTestRule.onNodeWithText("The Details page").assertIsDisplayed()
    }
}
