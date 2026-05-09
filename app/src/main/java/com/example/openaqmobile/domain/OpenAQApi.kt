package com.example.openaqmobile.domain

interface OpenAQApi {
    suspend fun getMeasurements(): List<Measurement>
}