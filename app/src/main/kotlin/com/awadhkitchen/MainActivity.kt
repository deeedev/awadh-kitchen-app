package com.awadhkitchen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.awadhkitchen.ui.navigation.AwadhKitchenNavigation
import com.awadhkitchen.ui.theme.AwadhKitchenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AwadhKitchenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AwadhKitchenNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
