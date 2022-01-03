package com.pmz.rocketweather.utils

sealed class ApiState<out T> {
    object Loading : ApiState<Nothing>()
    data class Success<T>(val data: T): ApiState<T>()
    data class Failure(val errorMsg: String) : ApiState<Nothing>()
}