package org.example.pantrywisecmp.product.presentation.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.toRoute
import org.jetbrains.compose.resources.StringResource
import pantrywisecmp.composeapp.generated.resources.*

fun ProductGraphRoutes.showBackButton(): Boolean = when (this) {
    is ProductGraphRoutes.SearchAndSelectProducts,
    ProductGraphRoutes.AddProductsWithCamera -> true
    else -> false
}

fun ProductGraphRoutes.showAddButton(): Boolean = when (this) {
    ProductGraphRoutes.Inventory,
    ProductGraphRoutes.ShoppingList -> true
    else -> false
}

fun ProductGraphRoutes.showBottomNavigationBar(): Boolean = when (this) {
    ProductGraphRoutes.Inventory,
    ProductGraphRoutes.ShoppingList -> true
    else -> false
}

fun ProductGraphRoutes.toolbarTextRes(): StringResource? = when (this) {
    ProductGraphRoutes.SearchAndSelectProducts,
    ProductGraphRoutes.AddProductsWithCamera -> Res.string.adding_product_header

    ProductGraphRoutes.Inventory -> Res.string.inventory_header
    ProductGraphRoutes.ShoppingList -> Res.string.shopping_list_header
    else -> null
}

fun NavBackStackEntry.toProductGraphRoute(): ProductGraphRoutes? {
    return when {
        destination.hasRoute<ProductGraphRoutes.Inventory>() -> ProductGraphRoutes.Inventory
        destination.hasRoute<ProductGraphRoutes.SearchAndSelectProducts>() -> ProductGraphRoutes.SearchAndSelectProducts
        destination.hasRoute<ProductGraphRoutes.AddProductsWithCamera>() -> ProductGraphRoutes.AddProductsWithCamera
        destination.hasRoute<ProductGraphRoutes.ShoppingList>() -> ProductGraphRoutes.ShoppingList
        else -> null
    }
}