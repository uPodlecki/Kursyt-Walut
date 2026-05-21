package com.example.kursyt_walut.network

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    val result: String,
    @SerializedName("base_code")
    val baseCode: String,
    @SerializedName("time_last_update_utc")
    val lastUpdate: String,
    @SerializedName("conversion_rates")
    val conversionRates: Map<String, Double>
)