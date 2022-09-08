package com.ikami.sampleloanapp.domain.models

sealed class NetworkResponse<out T> {
    object Loading : NetworkResponse<Nothing>()
    class Success<out T>(val data: T): NetworkResponse<T>()
    class Error(val e: String): NetworkResponse<Nothing>()
}