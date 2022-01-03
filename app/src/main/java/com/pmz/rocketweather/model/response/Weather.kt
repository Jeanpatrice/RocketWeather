package com.pmz.rocketweather.model.response


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    val days: List<Day>?,
    val id: Int?
)