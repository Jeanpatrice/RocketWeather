package com.pmz.rocketweather.model.response


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyWeather(
    val hour: Int,
    val humidity: Double,
    val rainChance: Double,
    val temperature: Int,
    val weatherType: String,
    val windSpeed: Double
)