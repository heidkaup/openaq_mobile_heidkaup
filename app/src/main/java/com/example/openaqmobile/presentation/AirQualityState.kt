package com.example.openaqmobile.presentation

import com.example.openaqmobile.domain.Measurement

data class AirQualityState(
    val loading: Boolean = false,
    val items: List<Measurement> = emptyList(),
    val error: String? = null
)
