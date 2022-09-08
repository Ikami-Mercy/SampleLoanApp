package com.ikami.sampleloanapp.domain.models

data class Loan(
    val due: Double,
    val dueDate: Long,
    val level: String,
    val status: String,
    val approved: Double
)