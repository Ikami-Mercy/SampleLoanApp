package com.ikami.sampleloanapp.domain.models

import com.google.gson.annotations.SerializedName

data class Locale(
    val currency: String,
    val loanLimit: Double,
    val timezone: Int
)

data class CountryLocale
    (
    @SerializedName("ke")
    val ke: Locale,
    @SerializedName("mx")
    val mx: Locale,
    @SerializedName("ph")
    val ph: Locale
)