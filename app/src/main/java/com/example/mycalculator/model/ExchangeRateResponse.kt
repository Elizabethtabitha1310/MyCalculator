package com.example.mycalculator.model


data class ExchangeRateResponse(
    val rates: Map<String, Double>,
    val base: String,
    val date: String
)

