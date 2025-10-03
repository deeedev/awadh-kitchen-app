package com.awadhkitchen.util

import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticsManager @Inject constructor() {

    private val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics
    private val crashlytics: FirebaseCrashlytics = Firebase.crashlytics

    fun logEvent(eventName: String, parameters: Map<String, Any> = emptyMap()) {
        try {
            val bundle = Bundle().apply {
                parameters.forEach { (key, value) ->
                    when (value) {
                        is String -> putString(key, value)
                        is Int -> putInt(key, value)
                        is Long -> putLong(key, value)
                        is Double -> putDouble(key, value)
                        is Boolean -> putBoolean(key, value)
                    }
                }
            }
            firebaseAnalytics.logEvent(eventName, bundle)
        } catch (e: Exception) {
            Log.e("AnalyticsManager", "Error logging event: $eventName", e)
        }
    }

    fun setUserProperty(key: String, value: String) {
        try {
            firebaseAnalytics.setUserProperty(key, value)
        } catch (e: Exception) {
            Log.e("AnalyticsManager", "Error setting user property: $key", e)
        }
    }

    fun setUserId(userId: String) {
        try {
            firebaseAnalytics.setUserId(userId)
            crashlytics.setUserId(userId)
        } catch (e: Exception) {
            Log.e("AnalyticsManager", "Error setting user ID: $userId", e)
        }
    }

    fun logException(throwable: Throwable, message: String? = null) {
        try {
            crashlytics.recordException(throwable)
            if (message != null) {
                crashlytics.log(message)
            }
        } catch (e: Exception) {
            Log.e("AnalyticsManager", "Error logging exception", e)
        }
    }

    fun logCustomKey(key: String, value: String) {
        try {
            crashlytics.setCustomKey(key, value)
        } catch (e: Exception) {
            Log.e("AnalyticsManager", "Error setting custom key: $key", e)
        }
    }

    // App-specific events
    fun logUserSignUp(method: String) {
        logEvent("user_sign_up", mapOf("method" to method))
    }

    fun logUserSignIn(method: String) {
        logEvent("user_sign_in", mapOf("method" to method))
    }

    fun logSubscriptionSelected(boxType: String, price: Double) {
        logEvent("subscription_selected", mapOf(
            "box_type" to boxType,
            "price" to price
        ))
    }

    fun logOrderPlaced(orderId: String, totalAmount: Double) {
        logEvent("order_placed", mapOf(
            "order_id" to orderId,
            "total_amount" to totalAmount
        ))
    }

    fun logScreenView(screenName: String) {
        logEvent("screen_view", mapOf("screen_name" to screenName))
    }
}
