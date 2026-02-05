package org.example.pantrywisecmp.product.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
@ConstructedBy(DatabaseConstructor::class)
abstract class PantryWiseDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
}