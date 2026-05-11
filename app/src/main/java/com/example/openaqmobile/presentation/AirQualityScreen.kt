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
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import com.example.openaqmobile.domain.Measurement


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirQualityScreen(vm: AirQualityViewModel, onItemClick: (Measurement) -> Unit
)
 {
    val state by vm.state.collectAsStateWithLifecycle()

    // yläpalkki ja reunat
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("OpenAQ Mobile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // eri tilat eli lataus, virhe ja datan näyttö
            when {
                state.loading -> {
                    // latausikoni keskellä ruutua, jos haku on käynnissä
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                state.error != null -> {
                    // virheviesti punaisella, jos haku epäonnistui
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(text = state.error!!, color = MaterialTheme.colorScheme.error)
                    }
                }
                else -> {

                    Text(
                        text = "Mittaukset Helsingistä",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(state.items) { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .clickable {
                                        onItemClick(item)
                                    },
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item.measured_at?.substring(5, 16) ?: "",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = "%.1f µg/m³".format(item.value),
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.primary

                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // päivitä pysyy alhaalla
            Button(
                onClick = { vm.fetchMeasurements() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Päivitä", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val style = MaterialTheme.typography.bodySmall
                Text("Heidi Kauppila", style = style)
                Text("Edistynyt mobiiliohjelmointi", style = style)
                Text("Kevät 2026", style = style)
            }

        }
    }
}