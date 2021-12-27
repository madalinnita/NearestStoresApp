package com.example.neareststoresapp.data.di

import com.example.core.usecases.GetStoresUseCase
import org.koin.dsl.module

val storesUseCases = module {
    single { GetStoresUseCase(get()) }
}