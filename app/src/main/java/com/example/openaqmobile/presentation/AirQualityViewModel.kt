package com.example.openaqmobile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.openaqmobile.OpenAQApplication
import com.example.openaqmobile.domain.OpenAQApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// rajapinta
class AirQualityViewModel(private val api: OpenAQApi) : ViewModel() {
    // sisäinen tila
    private val _state = MutableStateFlow(AirQualityState())
    // julkinen tila
    val state = _state.asStateFlow()

    init {
        // haetaan data kun ViewModel luodaan
        fetchMeasurements()
    }

    fun fetchMeasurements() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true, error = null) }
            try {
                // käytetään injektoitua APIa datan hakuun
                val response = api.getMeasurements(4588, "2023-01-01")
                _state.update { it.copy(loading = false, items = response) }
            } catch (e: Exception) {
                _state.update { it.copy(loading = false, error = "Virhe: ${e.message}") }
            }
        }
    }

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
