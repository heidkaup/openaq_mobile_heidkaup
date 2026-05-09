package com.example.openaqmobile.data

import com.example.openaqmobile.domain.Measurement
import com.example.openaqmobile.domain.OpenAQApi

class OpenAQApiImpl : OpenAQApi {
    override suspend fun getMeasurements(): List<Measurement> {
        // palautetaan testausta varten
        return listOf(
            Measurement(1, "Helsinki", "pm10", 22.4, "µg/m³"),
            Measurement(2, "Helsinki", "no2", 11.6, "µg/m³")
        )
    }
}