package com.pmz.rocketweather.model.remote

import com.pmz.rocketweather.model.response.CityResponse
import com.pmz.rocketweather.model.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BottleWeatherService {

    @GET("cities")
    suspend fun getCity(@Query("search") search: String): CityResponse

    @GET("cities/{geoid}")
    suspend fun getWeather(@Path("geoid") geoId: Int): WeatherResponse

    @GET("cities/{geoid}/radar")
    suspend fun getRadar(@Path("geoid") geoid: Long)
}