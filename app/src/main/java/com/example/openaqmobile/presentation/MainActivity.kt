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
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private val viewModel: AirQualityViewModel by viewModels {
        AirQualityViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            // käytetään siirrettyä teemaa
            OpenAQMobileTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "air_quality_list"
                ) {
                    // lista
                    composable("air_quality_list") {
                        AirQualityScreen(
                            vm = viewModel,
                            onItemClick = { item ->
                                navController.navigate(
                                    "detail/${item.value}/${item.measured_at}"
                                )
                            }
                        )
                    }

                    // details
                    composable("detail/{value}/{time}") { backStackEntry ->
                        val value = backStackEntry.arguments?.getString("value")
                        val time = backStackEntry.arguments?.getString("time")

                        DetailScreen(
                            value = value,
                            time = time,
                            onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}


