package com.pmz.rocketweather.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class City(
    @Json(name = "admin1 code")
    val admin1Code: String,
    @Json(name = "admin2 code")
    val admin2Code: Int,
    @Json(name = "admin3 code")
    val admin3Code: Any?,
    @Json(name = "admin4 code")
    val admin4Code: String,
    val alternatenames: String,
    val asciiname: String,
    val cc2: String,
    @Json(name = "country code")
    val countryCode: String,
    val dem: Int,
    val elevation: Int,
    @Json(name = "feature class")
    val featureClass: String,
    @Json(name = "feature code")
    val featureCode: String,
    val geonameid: Int,
    val imageURLs: ImageURLs,
    val latitude: Double,
    val longitude: Double,
    @Json(name = "modification date")
    val modificationDate: String,
    val name: String,
    val population: Int,
    val timezone: String
)