package com.example.neareststoresapp.data.di

import com.example.neareststoresapp.data.api.StoresApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
   fun provideStoresApi(retrofit: Retrofit): StoresApi {
      return retrofit.create(StoresApi::class.java)
   }
   single { provideStoresApi(get()) }
}