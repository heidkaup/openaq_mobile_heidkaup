package com.example.openaqmobile

import android.app.Application
import com.example.openaqmobile.domain.OpenAQApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenAQApplication : Application() {
    val api: OpenAQApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAQApi::class.java)
    }
}
