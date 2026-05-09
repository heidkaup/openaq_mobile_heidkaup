package com.example.openaqmobile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openaqmobile.domain.OpenAQApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AirQualityViewModel(private val api: OpenAQApi) : ViewModel() {
    private val _state = MutableStateFlow(AirQualityState())
    val state = _state.asStateFlow()

    fun loadData() {
        _state.update { it.copy(loading = true) }

        viewModelScope.launch {
            try {
                val data = api.getMeasurements()
                _state.update { it.copy(items = data, loading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(loading = false, err = e.message) }
            }
        }
    }
}