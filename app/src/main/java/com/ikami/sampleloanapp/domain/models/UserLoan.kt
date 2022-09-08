package com.ikami.sampleloanapp.domain.models

import com.google.gson.annotations.SerializedName
import com.ikami.sampleloanapp.domain.models.Loan

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