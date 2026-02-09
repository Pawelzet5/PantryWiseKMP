package org.example.pantrywisecmp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.pantrywisecmp.product.presentation.shopping_list.ShoppingListViewModel
import org.example.pantrywisecmp.product.data.repository.ProductRepositoryImpl
import org.example.pantrywisecmp.product.data.repository.SuggestionsRepositoryImpl
import org.example.pantrywisecmp.product.data.database.DatabaseFactory
import org.example.pantrywisecmp.product.data.database.PantryWiseDatabase
import org.example.pantrywisecmp.product.domain.repository.ProductRepository
import org.example.pantrywisecmp.product.domain.repository.SuggestionsRepository
import org.example.pantrywisecmp.product.domain.usecase.ProductQuantityValidationUseCase
import org.example.pantrywisecmp.product.presentation.adding_products.SearchAndSelectProductsViewModel
import org.example.pantrywisecmp.product.presentation.inventory.InventoryScreenViewModel
import org.example.pantrywisecmp.product.presentation.product_action_menu.ProductMenuViewModel
import org.example.pantrywisecmp.product.presentation.product_input.ProductInputViewModel
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

    singleOf(::ProductRepositoryImpl).bind<ProductRepository>()
    singleOf(::SuggestionsRepositoryImpl).bind<SuggestionsRepository>()

    viewModelOf(::InventoryScreenViewModel)
    viewModelOf(::ShoppingListViewModel)
    viewModelOf(::ProductMenuViewModel)
    viewModelOf(::ProductInputViewModel)
    viewModelOf(::SearchAndSelectProductsViewModel)
    factory { ProductQuantityValidationUseCase() }
}