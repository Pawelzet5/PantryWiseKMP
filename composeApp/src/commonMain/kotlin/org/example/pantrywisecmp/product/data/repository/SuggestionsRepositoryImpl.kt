package org.example.pantrywisecmp.product.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.pantrywisecmp.product.data.MockDataHelper
import org.example.pantrywisecmp.product.data.database.ProductDao
import org.example.pantrywisecmp.product.domain.ProductSuggestion
import org.example.pantrywisecmp.product.domain.repository.SuggestionsRepository


class SuggestionsRepositoryImpl(
    private val productDao: ProductDao
) : SuggestionsRepository {
    // TODO: Implement proper way to store and retrieve suggestions
    private val allProductNames =
        MockDataHelper.getSuggestionsList().map { ProductSuggestion(name = it, false) }

    override fun getSuggestions(query: String): List<ProductSuggestion> {
        return allProductNames.filter { it.name.contains(query, ignoreCase = true) }
    }

    override fun getRecentProductsSuggestions(): Flow<List<ProductSuggestion>> {
        return productDao.getRecentProductsNames()
            .map { it.map { productName -> ProductSuggestion(productName, false) } }
    }
}

