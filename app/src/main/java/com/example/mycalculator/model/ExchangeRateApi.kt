package com.example.mycalculator.model

import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateApi {
    @GET("latest")
    suspend fun getExchangeRates(
        @Query("USD") base: String
    ): String
}
