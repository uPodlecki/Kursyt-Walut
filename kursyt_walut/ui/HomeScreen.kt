package com.example.kursyt_walut.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kursyt_walut.CurrencyViewModel


@Composable
fun HomeScreen(viewModel: CurrencyViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchRates()
    }
    var searchQuery by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        if (viewModel.isOfflineMode) {
            Text(
                text = "Brak połączenia! Dane wczytane z pamięci.",
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {searchQuery = it},
            modifier = Modifier
                .fillMaxWidth().
                padding(8.dp),
            label = {Text("Szukaj Waluty")})
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (viewModel.errorMessage.isNotEmpty()) {
                Text(text = viewModel.errorMessage)
            } else if (viewModel.rates.isEmpty()) {
                CircularProgressIndicator()
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    val ratesList = viewModel.rates.filter{element->
                        element.key.contains(searchQuery, ignoreCase = true)
                    }.toList()
                    items(ratesList) { element ->
                        val invertedRate = 1.0 / element.second
                        CurrencyRow(currencyCode = element.first, rate = invertedRate)
                    }
                }
            }
        }
    }
}
@SuppressLint("DefaultLocale")
@Composable
fun CurrencyRow(currencyCode: String, rate: Double){
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = currencyCode)
            Text(text = String.format(java.util.Locale.getDefault(), "%.4f PLN", rate))
        }
    }
}