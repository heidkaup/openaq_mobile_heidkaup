package com.example.openaqmobile.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    value: String?,
    time: String?,
    onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("OpenAQ Mobile") },
                // back buttoni
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Text("Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->

    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Text(
            text = "Mittauksen tiedot",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = time ?: "")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = value ?: "")
        }
    }
}
