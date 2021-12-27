package com.example.neareststoresapp.data.api

import com.example.core.model.Stores
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface StoresApi {
    @Headers("Accept: application/xml")
    @GET("stores/")
    suspend fun getStores(@Query("longitude") longitude: Double?,
                          @Query("latitude") latitude: Double?,
                          @Query("radius") radius: Int = 1000,
                          @Query("page") page: Int = 1,
                          @Query("pageSize") pageSize: Int = 10,
                          @Query("clientApplicationKey") clientApplicationKey: String = "testApplication"): Response<Stores>
}
