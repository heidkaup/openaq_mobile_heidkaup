package com.example.openaqmobile.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AirQualityScreen(vm: AirQualityViewModel) {
    val state by vm.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "OpenAQ Mobile - Helsinki", style = MaterialTheme.typography.headlineMedium)

        LazyColumn {
            items(items = state.items, key = { it.id }) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(text = "${item.parameter}: ", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "${item.value} ${item.unit}")
                    }
                }
            }
        }

        Button(onClick = { vm.fetchMeasurements() }) {
            Text("Päivitä tiedot")
        }
    }
}
