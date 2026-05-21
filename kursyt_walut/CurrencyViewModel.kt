package com.example.kursyt_walut

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kursyt_walut.data.CacheManager
import com.example.kursyt_walut.data.SecureStorage
import com.example.kursyt_walut.network.RetrofitClient
import kotlinx.coroutines.launch

class CurrencyViewModel(application:Application) : AndroidViewModel(application){
    private val secureStorage = SecureStorage(application)
    private val cacheManager = CacheManager(application)
    var rates by mutableStateOf<Map<String, Double>>(emptyMap())
    var errorMessage by mutableStateOf("")
    var isOfflineMode by mutableStateOf(false)
    fun fetchRates(){
        val apiKey = secureStorage.getApiKey()
        if(apiKey.isNullOrEmpty()){
            errorMessage = ("Brak Klucza API")
            return
        }
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getLatestRates(apiKey, "PLN")
                rates = response.conversionRates
                cacheManager.saveRates(response.conversionRates)
                errorMessage = ""
                isOfflineMode = false
            }
            catch(e: Exception){
                val savedData = cacheManager.getRates()
                if(!savedData.isNullOrEmpty()){
                    rates = savedData
                    errorMessage = ""
                    isOfflineMode = true
                }else{
                    errorMessage = "Brak internetu i brak zapisanych danych: ${e.message}"
                }
            }
        }
    }
}