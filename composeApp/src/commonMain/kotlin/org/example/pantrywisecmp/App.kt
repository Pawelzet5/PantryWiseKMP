package org.example.pantrywisecmp

import androidx.compose.runtime.Composable
import org.example.pantrywisecmp.core.presentation.theme.PantryWiseTheme
import org.example.pantrywisecmp.product.presentation.adding_products.SearchAndSelectProductsScreenRoot

@Composable
fun App() {
    PantryWiseTheme {
        SearchAndSelectProductsScreenRoot()
    }
}