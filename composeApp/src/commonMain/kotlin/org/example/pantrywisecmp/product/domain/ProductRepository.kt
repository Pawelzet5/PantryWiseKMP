package org.example.pantrywisecmp.product.domain

import kotlinx.coroutines.flow.Flow
import org.example.pantrywisecmp.product.data.database.ProductEntity

interface ProductRepository {
    fun getProducts(): Flow<List<ProductEntity>>
    fun getRecentProducts(query: String): Flow<List<ProductSuggestion>>
    suspend fun addProduct(product: Product)
    suspend fun addProductList(productList: List<Product>)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun deleteProductList(productList: List<Product>)
}