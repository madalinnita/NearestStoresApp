package com.example.neareststoresapp.data.repositories

import com.example.core.model.Stores
import com.example.core.repositories.StoresRepository
import com.example.core.utils.AppResult
import com.example.neareststoresapp.data.api.StoresApi

class StoresRepositoryImpl(private val storesApi: StoresApi): StoresRepository {
    override suspend fun getStores(longitude: Double, latitude: Double): AppResult<Stores> {
        val response = storesApi.getStores(longitude, latitude)

        return if (response.isSuccessful) {
            AppResult.Success(response.body() ?: Stores())
        } else {
            AppResult.Error(Exception(response.message()))
        }
    }
}