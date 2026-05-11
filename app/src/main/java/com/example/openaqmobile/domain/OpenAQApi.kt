package com.example.openaqmobile.domain

import retrofit2.http.GET
import retrofit2.http.Headers

interface OpenAQApi {
    @Headers("X-API-Key: 0fa2bad4d6736109872dce1c457bffd4e743172f0c0e1dd0b2703aa302f37534")
    @GET("measurements")
    suspend fun getMeasurements(): List<Measurement>
}
