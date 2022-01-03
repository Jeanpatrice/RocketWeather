package com.pmz.rocketweather.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Day(
    val dayOfTheWeek: Int,
    val high: Int,
    val hourlyWeather: List<HourlyWeather>,
    val low: Int,
    val weatherType: String
)