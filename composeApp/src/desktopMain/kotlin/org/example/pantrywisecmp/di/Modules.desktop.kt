package org.example.pantrywisecmp.di

import org.example.pantrywisecmp.product.data.database.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { DatabaseFactory() }
    }