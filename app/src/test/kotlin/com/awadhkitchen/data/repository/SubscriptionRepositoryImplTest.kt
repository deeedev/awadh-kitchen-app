package com.awadhkitchen.data.repository

import com.awadhkitchen.data.model.BoxType
import com.awadhkitchen.data.model.Order
import com.awadhkitchen.data.model.OrderStatus
import com.awadhkitchen.data.model.SubscriptionBox
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SubscriptionRepositoryImplTest {

    private lateinit var subscriptionRepository: SubscriptionRepositoryImpl

    @Before
    fun setup() {
        subscriptionRepository = SubscriptionRepositoryImpl()
    }

    @Test
    fun `getSubscriptionBoxes should return list of subscription boxes`() = runTest {
        // When
        val result = subscriptionRepository.getSubscriptionBoxes()

        // Then
        assertTrue(result.isSuccess)
        val boxes = result.getOrNull()
        assertNotNull(boxes)
        assertTrue(boxes!!.isNotEmpty())
        assertEquals(3, boxes.size)
        
        // Verify Gold Box
        val goldBox = boxes.find { it.type == BoxType.GOLD }
        assertNotNull(goldBox)
        assertEquals("Gold Box", goldBox?.name)
        assertEquals(55.0, goldBox?.price, 0.01)
        assertEquals(4.8, goldBox?.rating, 0.01)
    }

    @Test
    fun `getSubscriptionBoxById should return specific subscription box`() = runTest {
        // Given
        val boxId = "1"

        // When
        val result = subscriptionRepository.getSubscriptionBoxById(boxId)

        // Then
        assertTrue(result.isSuccess)
        val box = result.getOrNull()
        assertNotNull(box)
        assertEquals(boxId, box?.id)
        assertEquals("Gold Box", box?.name)
        assertEquals(BoxType.GOLD, box?.type)
    }

    @Test
    fun `getUserOrders should return user orders`() = runTest {
        // Given
        val userId = "user123"

        // When
        val result = subscriptionRepository.getUserOrders(userId)

        // Then
        assertTrue(result.isSuccess)
        val orders = result.getOrNull()
        assertNotNull(orders)
        assertTrue(orders!!.isNotEmpty())
        assertEquals(2, orders.size)
        
        // Verify all orders belong to the user
        orders.forEach { order ->
            assertEquals(userId, order.userId)
        }
    }

    @Test
    fun `placeOrder should return confirmed order`() = runTest {
        // Given
        val order = Order(
            userId = "user123",
            subscriptionBox = SubscriptionBox(
                id = "1",
                name = "Gold Box",
                type = BoxType.GOLD
            ),
            quantity = 1,
            totalAmount = 55.0,
            status = OrderStatus.PENDING
        )

        // When
        val result = subscriptionRepository.placeOrder(order)

        // Then
        assertTrue(result.isSuccess)
        val placedOrder = result.getOrNull()
        assertNotNull(placedOrder)
        assertEquals(OrderStatus.CONFIRMED, placedOrder?.status)
        assertNotEquals(order.id, placedOrder?.id) // Should have new ID
    }
}
