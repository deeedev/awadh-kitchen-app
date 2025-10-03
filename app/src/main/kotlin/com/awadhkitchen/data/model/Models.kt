package com.awadhkitchen.data.model

data class User(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val profileImageUrl: String = "",
    val activeSubscription: SubscriptionBox? = null,
    val createdAt: Long = System.currentTimeMillis()
)

data class SubscriptionBox(
    val id: String = "",
    val name: String = "",
    val type: BoxType = BoxType.GOLD,
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val imageUrl: String = "",
    val description: String = "",
    val frequency: SubscriptionFrequency = SubscriptionFrequency.WEEKLY,
    val mealType: MealType = MealType.BOTH
)

enum class BoxType {
    GOLD, SILVER, PLATINUM
}

enum class SubscriptionFrequency {
    WEEKLY, MONTHLY, QUARTERLY
}

enum class MealType {
    LUNCH, DINNER, BOTH
}

data class Order(
    val id: String = "",
    val userId: String = "",
    val subscriptionBox: SubscriptionBox,
    val quantity: Int = 1,
    val totalAmount: Double = 0.0,
    val status: OrderStatus = OrderStatus.PENDING,
    val shippingAddress: ShippingAddress? = null,
    val paymentMethod: PaymentMethod? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val deliveryDate: Long? = null
)

enum class OrderStatus {
    PENDING, CONFIRMED, PREPARING, SHIPPED, DELIVERED, CANCELLED
}

data class ShippingAddress(
    val id: String = "",
    val fullName: String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val zipCode: String = "",
    val phoneNumber: String = "",
    val isDefault: Boolean = false
)

data class PaymentMethod(
    val id: String = "",
    val type: PaymentType = PaymentType.CREDIT_CARD,
    val lastFourDigits: String = "",
    val expiryMonth: Int = 0,
    val expiryYear: Int = 0,
    val cardholderName: String = ""
)

enum class PaymentType {
    CREDIT_CARD, DEBIT_CARD, PAYPAL, APPLE_PAY, GOOGLE_PAY
}
