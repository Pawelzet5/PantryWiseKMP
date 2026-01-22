package org.example.pantrywisecmp.product.presentation.inventoryScreen

import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.domain.ProductCategory


data class InventoryScreenState(
    val categoryToProductListMap: Map<ProductCategory, List<Product>> = emptyMap(),
    val selectedProduct: Product? = null,
    val expandedCategories: Set<ProductCategory> = emptySet(),
    val multiSelectionModeActive: Boolean = false
)
