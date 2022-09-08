package com.ikami.sampleloanapp.domain.models

//A helper class for displaying the different UI states
sealed class NetworkResponse<out T> {
    object Loading : NetworkResponse<Nothing>()
    class Success<out T>(val data: T): NetworkResponse<T>()
    class Error(val e: String): NetworkResponse<Nothing>()
}