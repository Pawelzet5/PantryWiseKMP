package org.example.pantrywisecmp.product.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.example.pantrywisecmp.product.presentation.adding_products.SearchAndSelectProductsScreenRoot
import org.example.pantrywisecmp.product.presentation.inventory.InventoryScreenRoot
import org.example.pantrywisecmp.product.presentation.shopping_list.ShoppingListScreenRoot

fun NavGraphBuilder.productGraph(
    navController: NavController
) {
    navigation<ProductGraphRoutes.Graph>(
        startDestination = ProductGraphRoutes.Inventory
    ) {
        composable<ProductGraphRoutes.Inventory> {
            InventoryScreenRoot()
        }
        composable<ProductGraphRoutes.ShoppingList> {
            ShoppingListScreenRoot()
        }
        composable<ProductGraphRoutes.SearchAndSelectProducts> {
            SearchAndSelectProductsScreenRoot(onNavigateBack = {
                navController.navigateUp()
            })
        }
    }
}