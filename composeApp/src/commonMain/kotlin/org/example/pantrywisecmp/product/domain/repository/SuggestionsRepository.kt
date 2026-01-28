package org.example.pantrywisecmp.product.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.pantrywisecmp.product.domain.ProductSuggestion

interface SuggestionsRepository {
    fun getSuggestions(query: String): List<ProductSuggestion>
    fun getRecentProductsSuggestions():  Flow<List<ProductSuggestion>>
}