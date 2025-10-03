package com.awadhkitchen.ui.auth

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.awadhkitchen.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun authScreen_displaysCorrectContent() {
        // Given
        composeTestRule.setContent {
            AuthScreen(onNavigateToHome = {})
        }

        // Then
        composeTestRule
            .onNodeWithText("The Awadh Kitchen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Create an account")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Enter your email to sign up for this app.")
            .assertIsDisplayed()
    }

    @Test
    fun authScreen_displaysEmailInput() {
        // Given
        composeTestRule.setContent {
            AuthScreen(onNavigateToHome = {})
        }

        // Then
        composeTestRule
            .onNodeWithText("email@domain.com")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Continue")
            .assertIsDisplayed()
    }

    @Test
    fun authScreen_displaysSocialLoginButtons() {
        // Given
        composeTestRule.setContent {
            AuthScreen(onNavigateToHome = {})
        }

        // Then
        composeTestRule
            .onNodeWithText("Continue with Google")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Continue with Apple")
            .assertIsDisplayed()
    }

    @Test
    fun authScreen_displaysTermsAndPrivacy() {
        // Given
        composeTestRule.setContent {
            AuthScreen(onNavigateToHome = {})
        }

        // Then
        composeTestRule
            .onNodeWithText("By clicking continue, you agree to our Terms of Service and Privacy Policy.")
            .assertIsDisplayed()
    }

    @Test
    fun authScreen_emailInputIsEditable() {
        // Given
        composeTestRule.setContent {
            AuthScreen(onNavigateToHome = {})
        }

        // When
        composeTestRule
            .onNodeWithText("email@domain.com")
            .performTextInput("test@example.com")

        // Then
        composeTestRule
            .onNodeWithText("test@example.com")
            .assertIsDisplayed()
    }

    @Test
    fun authScreen_continueButtonIsEnabledWithValidEmail() {
        // Given
        composeTestRule.setContent {
            AuthScreen(onNavigateToHome = {})
        }

        // When
        composeTestRule
            .onNodeWithText("email@domain.com")
            .performTextInput("test@example.com")

        // Then
        composeTestRule
            .onNodeWithText("Continue")
            .assertIsEnabled()
    }
}
