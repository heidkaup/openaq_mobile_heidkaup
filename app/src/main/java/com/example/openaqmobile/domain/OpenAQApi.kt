package com.example.openaqmobile.domain

import retrofit2.http.GET

interface OpenAQApi {
    @GET("measurements")
    suspend fun getMeasurements(): List<Measurement>
}
