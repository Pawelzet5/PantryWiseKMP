package org.example.pantrywisecmp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.pantrywisecmp.product.data.InventoryRepository
import org.example.pantrywisecmp.product.data.database.DatabaseFactory
import org.example.pantrywisecmp.product.data.database.PantryWiseDatabase
import org.example.pantrywisecmp.product.domain.ProductRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<PantryWiseDatabase>().productDao() }

}