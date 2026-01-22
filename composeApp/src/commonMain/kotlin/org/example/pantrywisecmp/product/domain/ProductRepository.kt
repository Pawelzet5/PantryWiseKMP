package org.example.pantrywisecmp.product.domain

import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(productStatus: ProductStatus): Flow<List<Product>>
    fun getRecentProducts(query: String): Flow<List<ProductSuggestion>>
    suspend fun addProduct(product: Product)
    suspend fun addProductList(productList: List<Product>)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun deleteProductList(productIdSet: Set<Int>)
}