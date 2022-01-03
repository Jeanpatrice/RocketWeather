package com.pmz.rocketweather.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageURLs(
    @Json(name = "androidImageURLs")
    val androidImageURLs: AndroidImageURLs?
)