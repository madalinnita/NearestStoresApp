package com.example.neareststoresapp.presentation

import android.app.Application
import com.example.neareststoresapp.BuildConfig
import com.example.neareststoresapp.data.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NearestStoresApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NearestStoresApplication)
            if (BuildConfig.DEBUG) {
                androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            }
            modules(
                arrayListOf(
                    apiModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    storesUseCases
                )
            )
        }
    }
}