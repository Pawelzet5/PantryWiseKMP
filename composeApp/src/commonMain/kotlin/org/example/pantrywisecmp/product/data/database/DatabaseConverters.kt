package org.example.pantrywisecmp.product.data.database

import androidx.room.TypeConverter
import org.example.pantrywisecmp.product.domain.ProductCategory
import org.example.pantrywisecmp.product.domain.ProductUnit

class DatabaseConverters {

    @TypeConverter
    fun fromProductUnit(productUnit: ProductUnit): Int {
        return productUnit.ordinal
    }

    @TypeConverter
    fun toProductUnit(ordinal: Int): ProductUnit {
        return ProductUnit.entries[ordinal]
    }

    @TypeConverter
    fun fromProductCategory(productCategory: ProductCategory): Int {
        return productCategory.ordinal
    }

    @TypeConverter
    fun toProductCategory(ordinal: Int): ProductCategory {
        return ProductCategory.entries[ordinal]
    }
} 