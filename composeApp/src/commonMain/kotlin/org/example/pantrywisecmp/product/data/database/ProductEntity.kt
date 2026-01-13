package org.example.pantrywisecmp.product.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.pantrywisecmp.product.data.database.DatabaseConstants.PRODUCT_TABLE
import org.example.pantrywisecmp.product.domain.ProductCategory
import org.example.pantrywisecmp.product.domain.ProductStatus
import org.example.pantrywisecmp.product.domain.ProductUnit

@Entity(tableName = PRODUCT_TABLE)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val details: String,
    var quantity: Double?,
    var productUnit: ProductUnit,
    var category: ProductCategory,
    var productStatus: ProductStatus,
    var expirationDate: Long? = null
)