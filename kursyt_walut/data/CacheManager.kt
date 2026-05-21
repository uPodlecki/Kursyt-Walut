package com.example.kursyt_walut.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CacheManager(context: Context) {
    private val prefs = context.getSharedPreferences("currency_cache", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveRates(rates: Map<String, Double>) {
        val jsonString = gson.toJson(rates)
        prefs.edit().putString("SAVED_RATES", jsonString).apply()
    }

    fun getRates(): Map<String, Double>? {
        val jsonString = prefs.getString("SAVED_RATES", null)

        if (jsonString != null) {
            val type = object : TypeToken<Map<String, Double>>() {}.type
            return gson.fromJson(jsonString, type)
        }
        return null
    }
}