package com.wednesday.test

import android.app.Application
import com.wednesday.test.dependencies.catApiModule
import com.wednesday.test.dependencies.catModule
import com.wednesday.test.dependencies.networkModule
import com.wednesday.test.dependencies.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        //Setup di
        startKoin {
            androidContext(this@BaseApp)
            modules(
                viewModelsModule,
                networkModule,
                catApiModule,
                catModule
            )
        }
    }
}