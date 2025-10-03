package com.awadhkitchen.data.repository

import com.awadhkitchen.data.model.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AuthRepositoryImplTest {

    private lateinit var authRepository: AuthRepositoryImpl

    @Before
    fun setup() {
        authRepository = AuthRepositoryImpl()
    }

    @Test
    fun `signInWithEmail should return success with valid credentials`() = runTest {
        // Given
        val email = "test@example.com"
        val password = "password123"

        // When
        val result = authRepository.signInWithEmail(email, password)

        // Then
        assertTrue(result.isSuccess)
        val user = result.getOrNull()
        assertNotNull(user)
        assertEquals(email, user?.email)
        assertEquals("Test User", user?.name)
    }

    @Test
    fun `signUpWithEmail should return success with valid credentials`() = runTest {
        // Given
        val email = "newuser@example.com"
        val password = "password123"

        // When
        val result = authRepository.signUpWithEmail(email, password)

        // Then
        assertTrue(result.isSuccess)
        val user = result.getOrNull()
        assertNotNull(user)
        assertEquals(email, user?.email)
    }

    @Test
    fun `signInWithGoogle should return success`() = runTest {
        // When
        val result = authRepository.signInWithGoogle()

        // Then
        assertTrue(result.isSuccess)
        val user = result.getOrNull()
        assertNotNull(user)
        assertEquals("test@gmail.com", user?.email)
        assertEquals("Google User", user?.name)
    }

    @Test
    fun `signInWithApple should return success`() = runTest {
        // When
        val result = authRepository.signInWithApple()

        // Then
        assertTrue(result.isSuccess)
        val user = result.getOrNull()
        assertNotNull(user)
        assertEquals("test@icloud.com", user?.email)
        assertEquals("Apple User", user?.name)
    }

    @Test
    fun `isUserSignedIn should return true`() = runTest {
        // When
        val isSignedIn = authRepository.isUserSignedIn()

        // Then
        assertTrue(isSignedIn)
    }
}
