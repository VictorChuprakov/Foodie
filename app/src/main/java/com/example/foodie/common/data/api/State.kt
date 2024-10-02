package com.example.foodie.common.data.api

sealed class State<out T> {
    object Loading: State<Nothing>()
    data class Success<out T>(val data: T) : State<T>()
    data class Error(val error: ApiError) : State<Nothing>()
}

enum class ApiError {
    NETWORK_ERROR,
    RESPONSE_NULL,
    REQUEST_FAILED,
    UNEXPECTED_ERROR
}