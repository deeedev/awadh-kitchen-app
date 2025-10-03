package com.awadhkitchen.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.awadhkitchen.R
import com.awadhkitchen.data.model.BoxType
import com.awadhkitchen.data.model.SubscriptionBox
import com.awadhkitchen.ui.theme.AwadhKitchenTheme
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Work

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToCheckout: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val subscriptionBoxes = remember {
        listOf(
            SubscriptionBox(
                id = "1",
                name = "Gold Box",
                type = BoxType.GOLD,
                price = 55.0,
                rating = 4.8,
                reviewCount = 500,
                imageUrl = "",
                description = "Premium meal subscription with gourmet options"
            ),
            SubscriptionBox(
                id = "2",
                name = "Silver Box",
                type = BoxType.SILVER,
                price = 55.0,
                rating = 4.8,
                reviewCount = 500,
                imageUrl = "",
                description = "Quality meal subscription with balanced options"
            )
        )
    }

    AwadhKitchenTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "9:41",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.filter),
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.clickable { /* Handle filter */ }
                                )
                                Text(
                                    text = stringResource(R.string.sort),
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.clickable { /* Handle sort */ }
                                )
                                Text(
                                    text = stringResource(R.string.results_count),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            bottomBar = {
                BottomNavigationBar(
                    onNavigateToProfile = onNavigateToProfile
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(subscriptionBoxes) { subscriptionBox ->
                    SubscriptionBoxCard(
                        subscriptionBox = subscriptionBox,
                        onSelect = onNavigateToCheckout
                    )
                }
            }
        }
    }
}

@Composable
fun SubscriptionBoxCard(
    subscriptionBox: SubscriptionBox,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image placeholder
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .fillMaxHeight()
                    .background(Color.Gray.copy(alpha = 0.3f))
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = "Subscription Box Image",
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center),
                    tint = Color.Gray
                )
            }
            
            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = subscriptionBox.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = "${subscriptionBox.rating} (${subscriptionBox.reviewCount} reviews)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "$${subscriptionBox.price.toInt()} / Box",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Button(
                        onClick = onSelect,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.select),
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    onNavigateToProfile: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text(stringResource(R.string.home)) },
            selected = true,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
            label = { Text("Calendar") },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Work, contentDescription = "Work") },
            label = { Text("Work") },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorite") },
            label = { Text("Favorite") },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text(stringResource(R.string.profile)) },
            selected = false,
            onClick = onNavigateToProfile
        )
    }
}
