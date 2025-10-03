package com.awadhkitchen.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.awadhkitchen.data.model.BoxType
import com.awadhkitchen.data.model.Order
import com.awadhkitchen.data.model.OrderStatus
import com.awadhkitchen.data.model.SubscriptionBox
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseSubscriptionRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : SubscriptionRepository {

    override suspend fun getSubscriptionBoxes(): Result<List<SubscriptionBox>> {
        return try {
            val snapshot = firestore.collection("subscription_boxes")
                .orderBy("name", Query.Direction.ASCENDING)
                .get()
                .await()

            val boxes = snapshot.documents.mapNotNull { document ->
                try {
                    document.toObject(SubscriptionBox::class.java)?.copy(id = document.id)
                } catch (e: Exception) {
                    null
                }
            }
            Result.success(boxes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getSubscriptionBoxById(id: String): Result<SubscriptionBox> {
        return try {
            val document = firestore.collection("subscription_boxes")
                .document(id)
                .get()
                .await()

            if (document.exists()) {
                val box = document.toObject(SubscriptionBox::class.java)?.copy(id = document.id)
                if (box != null) {
                    Result.success(box)
                } else {
                    Result.failure(Exception("Failed to parse subscription box"))
                }
            } else {
                Result.failure(Exception("Subscription box not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserOrders(userId: String): Result<List<Order>> {
        return try {
            val snapshot = firestore.collection("orders")
                .whereEqualTo("userId", userId)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            val orders = snapshot.documents.mapNotNull { document ->
                try {
                    document.toObject(Order::class.java)?.copy(id = document.id)
                } catch (e: Exception) {
                    null
                }
            }
            Result.success(orders)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun placeOrder(order: Order): Result<Order> {
        return try {
            val orderData = order.copy(
                id = "", // Let Firestore generate the ID
                status = OrderStatus.CONFIRMED
            )
            
            val documentRef = firestore.collection("orders").add(orderData).await()
            val newOrder = orderData.copy(id = documentRef.id)
            
            Result.success(newOrder)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
