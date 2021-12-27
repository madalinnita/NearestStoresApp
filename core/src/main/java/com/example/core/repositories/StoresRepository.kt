package com.example.core.repositories

import com.example.core.model.Stores
import com.example.core.utils.AppResult

interface StoresRepository {
    suspend fun getStores(longitude: Double, latitude: Double): AppResult<Stores>
}