package org.example.pantrywisecmp.product.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.example.pantrywisecmp.product.data.database.DatabaseConstants.PRODUCT_TABLE
import org.example.pantrywisecmp.product.domain.ProductStatus

@Dao
interface ProductDao {

    @Query("""SELECT * FROM $PRODUCT_TABLE WHERE productStatus = :status  ORDER BY id ASC""")
    fun getProductListByStatus(status: ProductStatus): Flow<List<ProductEntity>>

    @Query("""SELECT name FROM $PRODUCT_TABLE ORDER BY id ASC""")
    fun getRecentProductsNames(): Flow<List<String>>

    @Query("""SELECT * FROM $PRODUCT_TABLE WHERE productStatus = "SHOPPING_LIST" ORDER BY id ASC""")
    fun getShoppingList(): Flow<List<ProductEntity>>

    @Upsert
    suspend fun upsertProduct(dbProduct: ProductEntity)

    @Upsert
    suspend fun upsertProductList(dbProductList: List<ProductEntity>)

    @Delete
    suspend fun deleteProduct(dbProduct: ProductEntity)

    @Query("""DELETE FROM $PRODUCT_TABLE WHERE id IN (:dbProductIdSet) """)
    suspend fun deleteProductListByIds(dbProductIdSet: Set<Int>)
}