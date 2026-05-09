package com.example.openaqmobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.openaqmobile.presentation.theme.OpenAQMobileTheme
import com.example.openaqmobile.data.OpenAQApiImpl
import androidx.activity.viewModels

class MainActivity : ComponentActivity() {
    private val viewModel: AirQualityViewModel by viewModels {
        AirQualityViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // alustetaan data ja ViewModel
        // ViewModel saa rajapinnan Interface konstruktorissa
        val api = OpenAQApiImpl()
        val viewModel = AirQualityViewModel(api)

        setContent {
            // käytetään siirrettyä teemaa
            OpenAQMobileTheme {
                // kutsutaan päänäkymää ja välitetään ViewModel
                AirQualityScreen(vm = viewModel)
            }
        }
    }
}
