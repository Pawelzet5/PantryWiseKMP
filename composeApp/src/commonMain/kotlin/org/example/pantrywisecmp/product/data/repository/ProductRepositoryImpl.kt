package org.example.pantrywisecmp.product.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.pantrywisecmp.product.data.MockDataHelper
import org.example.pantrywisecmp.product.data.database.ProductDao
import org.example.pantrywisecmp.product.data.mappers.toProduct
import org.example.pantrywisecmp.product.data.mappers.toProductEntity
import org.example.pantrywisecmp.product.domain.*
import org.example.pantrywisecmp.product.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val productDao: ProductDao
) : ProductRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getProducts(productStatus: ProductStatus): Flow<List<Product>> =
        productDao.getProductListByStatus(productStatus).map {
            if (it.isEmpty())
                MockDataHelper.getMockProductList()
            else it.map { productEntity -> productEntity.toProduct() }
        }


    override fun getRecentProducts(query: String): Flow<List<ProductSuggestion>> {
        // TODO Implement proper recent products functionality with dedicated table.
        return productDao.getRecentProductsNames().map { dbProducts ->
            dbProducts.filter { it.contains(query) }.map {
                ProductSuggestion(it, false)
            }
        }
    }

    override suspend fun addProduct(product: Product) {
        val dbProduct = product.toProductEntity()
        productDao.upsertProduct(dbProduct)
    }

    override suspend fun addProductList(productList: List<Product>) {
        productDao.upsertProductList(productList.map { it.toProductEntity() })
    }

    override suspend fun updateProduct(product: Product) {
        val dbProduct = product.toProductEntity()
        productDao.upsertProduct(dbProduct)
    }

    override suspend fun deleteProduct(product: Product) {
        val dbProduct = product.toProductEntity()
        productDao.deleteProduct(dbProduct)
    }

    override suspend fun deleteProductList(productIdSet: Set<Int>) {
        productDao.deleteProductListByIds(productIdSet)
    }
}