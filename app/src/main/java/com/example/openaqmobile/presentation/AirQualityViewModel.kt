package com.example.openaqmobile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.openaqmobile.OpenAQApplication
import com.example.openaqmobile.domain.OpenAQApi

// rajapinta
class AirQualityViewModel(private val api: OpenAQApi) : ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // haetaan API OpenAQApplicationista
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as OpenAQApplication)
                AirQualityViewModel(api = application.api)
            }
        }
    }
}
