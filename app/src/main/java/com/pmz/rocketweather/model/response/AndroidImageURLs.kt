package com.pmz.rocketweather.model.response


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AndroidImageURLs(
    val hdpiImageURL: String?,
    val mdpiImageURL: String?,
    val xhdpiImageURL: String?
)