package com.pmz.rocketweather.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val city: City?,
    val weather: Weather?
)