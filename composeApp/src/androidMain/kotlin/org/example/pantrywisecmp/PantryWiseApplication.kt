package org.example.pantrywisecmp

import android.app.Application
import org.example.pantrywisecmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class PantryWiseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@PantryWiseApplication)
        }
    }
}