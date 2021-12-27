package com.example.neareststoresapp.data.di

import com.example.neareststoresapp.presentation.storeslist.StoresListViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single { StoresListViewModel(get(), get()) }

}