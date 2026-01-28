package org.example.pantrywisecmp.product.presentation.product_action_menu

import org.example.pantrywisecmp.core.domain.UiText
import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.domain.ProductDraft
import org.example.pantrywisecmp.product.domain.ProductUnit

data class ProductMenuState(
    val selectedProduct: Product? = null,
    val unit: ProductUnit = ProductUnit.MILLILITER,
    val quantity: String = "",
    val quantityErrorText: UiText? = null,
    val productDraft: ProductDraft? = null
) {
    constructor(product: Product) : this(
        selectedProduct = product,
        quantity = product.quantity?.toString()?: "",
        unit = product.productUnit
    )
}
