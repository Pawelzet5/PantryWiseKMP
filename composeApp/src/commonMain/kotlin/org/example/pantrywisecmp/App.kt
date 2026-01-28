package org.example.pantrywisecmp

import androidx.compose.runtime.Composable
import org.example.pantrywisecmp.core.presentation.theme.PantryWiseTheme
import org.example.pantrywisecmp.product.presentation.inventory.InventoryScreenRoot

@Composable
fun App() {
    PantryWiseTheme {
        InventoryScreenRoot()
    }
}