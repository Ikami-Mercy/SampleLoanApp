package com.ikami.sampleloanapp.domain.models

import com.google.gson.annotations.SerializedName

data class UserLoan(
    @SerializedName("loan")
    val loan: Loan?,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("username")
    val username: String
)