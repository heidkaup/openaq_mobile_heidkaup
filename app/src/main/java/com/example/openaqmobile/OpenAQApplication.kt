package com.example.openaqmobile

import android.app.Application
import com.example.openaqmobile.domain.OpenAQApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenAQApplication : Application() {
    val api: OpenAQApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openaq.org/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAQApi::class.java)
    }
}
