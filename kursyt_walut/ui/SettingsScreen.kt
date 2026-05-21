package com.example.kursyt_walut.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kursyt_walut.data.SecureStorage


@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val secureStorage = remember { SecureStorage(context) }
    var apiKeyInput by remember { mutableStateOf(secureStorage.getApiKey()?:"") }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ustawienia aplikacji")
        OutlinedTextField(
            value = apiKeyInput,
            onValueChange = { apiKeyInput = it },
            label = {Text("Wpisz klucz API")},
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { secureStorage.saveApiKey(apiKeyInput) }) {
            Text("Zapisz")
            Toast.makeText(context, "Zapisano!", Toast.LENGTH_SHORT).show()
        }
    }
}