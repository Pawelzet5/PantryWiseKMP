package org.example.pantrywisecmp.product.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.pantrywisecmp.product.data.database.*
import org.example.pantrywisecmp.product.data.mappers.toProductEntity
import org.example.pantrywisecmp.product.domain.*

class ProductRepositoryImpl(
    private val productDao: ProductDao
) : ProductRepository {
    override fun getProducts(): Flow<List<ProductEntity>> =
        productDao.getProductListByStatus(ProductStatus.INVENTORY)


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

    override suspend fun deleteProductList(productList: List<Product>) {
        productDao.deleteProductList(productList.map { it.toProductEntity() })
    }
}