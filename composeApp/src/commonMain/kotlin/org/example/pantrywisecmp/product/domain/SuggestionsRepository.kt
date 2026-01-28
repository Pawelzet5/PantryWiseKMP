package org.example.pantrywisecmp.product.domain

interface SuggestionsRepository {
    fun getSuggestions(query: String): List<ProductSuggestion>
}