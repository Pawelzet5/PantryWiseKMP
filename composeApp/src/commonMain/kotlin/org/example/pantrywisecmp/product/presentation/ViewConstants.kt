package org.example.pantrywisecmp.product.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import org.example.pantrywisecmp.product.presentation.components.FancyBottomBarItem
import org.example.pantrywisecmp.product.presentation.navigation.ProductGraphRoutes

val navigationBarItems = listOf(
    FancyBottomBarItem(
        ProductGraphRoutes.Inventory,
        icon = Icons.AutoMirrored.Filled.List,
        contentDescription = "Inventory"
    ),
    FancyBottomBarItem(
        ProductGraphRoutes.ShoppingList,
        icon = Icons.Default.ShoppingCart,
        contentDescription = "Shopping list"
    )
)