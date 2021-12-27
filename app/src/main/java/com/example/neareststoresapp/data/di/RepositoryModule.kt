package com.example.neareststoresapp.data.di

import com.example.core.repositories.StoresRepository
import com.example.neareststoresapp.data.api.StoresApi
import com.example.neareststoresapp.data.repositories.StoresRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    fun provideStoresRepository(storesApi: StoresApi): StoresRepository {
        return StoresRepositoryImpl(storesApi)
    }
    single { provideStoresRepository(get()) }
}