package com.awadhkitchen.ui.home

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.awadhkitchen.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysTopBar() {
        // Given
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToCheckout = {},
                onNavigateToProfile = {}
            )
        }

        // Then
        composeTestRule
            .onNodeWithText("9:41")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Filter")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Sort")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("99 results")
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysSubscriptionBoxes() {
        // Given
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToCheckout = {},
                onNavigateToProfile = {}
            )
        }

        // Then
        composeTestRule
            .onNodeWithText("Gold Box")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Silver Box")
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysRatingsAndPrices() {
        // Given
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToCheckout = {},
                onNavigateToProfile = {}
            )
        }

        // Then
        composeTestRule
            .onNodeWithText("4.8 (500 reviews)")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("$55 / Box")
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysSelectButtons() {
        // Given
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToCheckout = {},
                onNavigateToProfile = {}
            )
        }

        // Then
        composeTestRule
            .onAllNodesWithText("Select")
            .assertCountEquals(2)
    }

    @Test
    fun homeScreen_displaysBottomNavigation() {
        // Given
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToCheckout = {},
                onNavigateToProfile = {}
            )
        }

        // Then
        composeTestRule
            .onNodeWithText("Home")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Profile")
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_selectButtonNavigatesToCheckout() {
        // Given
        var navigatedToCheckout = false
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToCheckout = { navigatedToCheckout = true },
                onNavigateToProfile = {}
            )
        }

        // When
        composeTestRule
            .onAllNodesWithText("Select")[0]
            .performClick()

        // Then
        assert(navigatedToCheckout)
    }
}
