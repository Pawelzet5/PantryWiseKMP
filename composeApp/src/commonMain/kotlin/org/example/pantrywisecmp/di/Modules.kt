package org.example.pantrywisecmp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.pantrywisecmp.product.data.ProductRepositoryImpl
import org.example.pantrywisecmp.product.data.database.DatabaseFactory
import org.example.pantrywisecmp.product.data.database.PantryWiseDatabase
import org.example.pantrywisecmp.product.domain.ProductRepository
import org.example.pantrywisecmp.product.domain.usecase.ProductQuantityValidationUseCase
import org.example.pantrywisecmp.product.presentation.inventoryScreen.InventoryScreenViewModel
import org.example.pantrywisecmp.product.presentation.productActionMenu.ProductMenuViewModel
import org.example.pantrywisecmp.product.presentation.productInputScreen.ProductInputViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
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
    singleOf(::ProductRepositoryImpl).bind<ProductRepository>() //TODO Rozdziel ProductRepo na Inventory i ShoppingList?

    viewModelOf(::InventoryScreenViewModel)
    viewModelOf(::ProductMenuViewModel)
    viewModelOf(::ProductInputViewModel)
    factory { ProductQuantityValidationUseCase() }
}