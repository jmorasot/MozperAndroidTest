package com.example.mozpertest.features.core.domain.models

sealed class ResultWrapper<out T> {
    object Loading : ResultWrapper<Nothing>()
    data class Success<out T : Any>(val data: T) : ResultWrapper<T>()
    data class Failure(val error: Throwable) : ResultWrapper<Nothing>()
}
