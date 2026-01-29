package org.example.pantrywisecmp.product.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface ProductGraphRoutes {
    @Serializable
    data object Graph: ProductGraphRoutes

    @Serializable
    data object Inventory: ProductGraphRoutes
    @Serializable
    data object SearchAndSelectProducts: ProductGraphRoutes
    @Serializable
    data object AddProductsWithCamera: ProductGraphRoutes
    @Serializable
    data object ShoppingList: ProductGraphRoutes
}