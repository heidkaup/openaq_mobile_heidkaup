package com.example.openaqmobile.domain

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenAQApi {
    @GET("api/measurements")
    suspend fun getMeasurements(
        @Query("location_id") locationId: Int,
        @Query("day") day: String
    ): List<Measurement>
}
