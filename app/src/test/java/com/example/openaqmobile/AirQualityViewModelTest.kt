package com.example.openaqmobile

import com.example.openaqmobile.domain.OpenAQApi
import com.example.openaqmobile.domain.Measurement
import com.example.openaqmobile.presentation.AirQualityViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test


class MockOpenAQApi(
    private val result: List<Measurement> = emptyList(),
    private val error: String? = null
) : OpenAQApi {
    override suspend fun getMeasurements(): List<Measurement> {
        if (error != null) throw Exception(error)
        return result
    }
}

class AirQualityViewModelTest {

    @Test
    fun `test fetch measurements success updates state items`() = runTest {
        val expectedData = listOf(Measurement(1, "pm10", 15.5, "µg/m³"))
        val mockApi = MockOpenAQApi(result = expectedData)
        val vm = AirQualityViewModel(mockApi)

        vm.fetchMeasurements()

        val currentState = vm.state.value
        assertEquals(expectedData, currentState.items)
        assertFalse(currentState.loading)
        assertNull(currentState.error)
    }
}
