package com.example.openaqmobile.domain

data class Measurement(
    val id: Int,
    val location: String,
    val parameter: String,
    val value: Double,
    val unit: String
)