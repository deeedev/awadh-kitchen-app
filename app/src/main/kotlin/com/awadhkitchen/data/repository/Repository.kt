package com.awadhkitchen.data.repository

import com.awadhkitchen.data.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInWithEmail(email: String, password: String): Result<User>
    suspend fun signUpWithEmail(email: String, password: String): Result<User>
    suspend fun signInWithGoogle(): Result<User>
    suspend fun signInWithApple(): Result<User>
    suspend fun signOut()
    fun getCurrentUser(): Flow<User?>
    fun isUserSignedIn(): Boolean
}

interface SubscriptionRepository {
    suspend fun getSubscriptionBoxes(): Result<List<com.awadhkitchen.data.model.SubscriptionBox>>
    suspend fun getSubscriptionBoxById(id: String): Result<com.awadhkitchen.data.model.SubscriptionBox>
    suspend fun getUserOrders(userId: String): Result<List<com.awadhkitchen.data.model.Order>>
    suspend fun placeOrder(order: com.awadhkitchen.data.model.Order): Result<com.awadhkitchen.data.model.Order>
}
