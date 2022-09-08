package com.ikami.sampleloanapp.domain.models

import java.lang.Exception


data class Response<T>(
    val wasSuccessful: Boolean,
    val exception: Exception?,
    val body: T
)
