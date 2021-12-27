package com.example.core.usecases

import com.example.core.model.Stores
import com.example.core.repositories.StoresRepository
import com.example.core.utils.AppResult

class GetStoresUseCase(private val storesRepository: StoresRepository) {
    suspend fun execute(longitude: Double, latitude: Double): AppResult<Stores> {
        return storesRepository.getStores(longitude, latitude)
    }
}