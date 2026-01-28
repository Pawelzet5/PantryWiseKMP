package org.example.pantrywisecmp.product.domain.repository

import org.example.pantrywisecmp.product.domain.ProductSuggestion

interface SuggestionsRepository {
    fun getSuggestions(query: String): List<ProductSuggestion>
    fun getRecentProductsSuggestions(): List<ProductSuggestion>
}