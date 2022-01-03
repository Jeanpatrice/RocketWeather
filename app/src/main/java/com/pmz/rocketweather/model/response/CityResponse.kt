package com.pmz.rocketweather.model.response


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityResponse(
    val cities: List<City>,
    val startIndex: Int,
    val totalCitiesFound: Int
)