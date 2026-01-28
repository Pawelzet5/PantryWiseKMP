package org.example.pantrywisecmp.product.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.domain.ProductStatus
import org.example.pantrywisecmp.product.domain.ProductSuggestion

interface ProductRepository {
    fun getProducts(productStatus: ProductStatus): Flow<List<Product>>
    fun getRecentProducts(query: String): Flow<List<ProductSuggestion>>
    suspend fun addProduct(product: Product)
    suspend fun addProductList(productList: List<Product>)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun deleteProductList(productIdSet: Set<Int>)
}