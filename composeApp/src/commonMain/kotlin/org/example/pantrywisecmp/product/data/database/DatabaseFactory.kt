package org.example.pantrywisecmp.product.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<PantryWiseDatabase>
}