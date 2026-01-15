package org.example.pantrywisecmp.product.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DatabaseConstructor: RoomDatabaseConstructor<PantryWiseDatabase> {
    override fun initialize(): PantryWiseDatabase
}