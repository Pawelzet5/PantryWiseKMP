package org.example.pantrywisecmp.product.presentation.product_input

import org.example.pantrywisecmp.product.domain.ProductDraft

interface ProductInputEffect {
    data class ValidationSuccess(val draft: ProductDraft) : ProductInputEffect
}