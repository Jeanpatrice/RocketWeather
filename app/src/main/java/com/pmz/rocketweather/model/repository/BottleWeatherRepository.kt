package com.pmz.rocketweather.model.repository

import com.pmz.rocketweather.model.custom.WeatherView
import com.pmz.rocketweather.model.local.WeatherDao
import com.pmz.rocketweather.model.remote.BottleWeatherService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BottleWeatherRepository @Inject constructor(
    private val weatherService: BottleWeatherService,
    private val weatherDao: WeatherDao,
) {

    val weather get() = weatherDao.getWeather()

    suspend fun getWeather(city: String) = try {
        val cityResponse = fetchCity(city).getOrThrow()
        val weatherResponse = fetchWeather(cityResponse.cities.first().geonameid).getOrThrow()
        val weather = WeatherView.convert(cityResponse, weatherResponse)
            ?: throw IllegalStateException("WeatherView is null")
        weatherDao.insertWeather(weather)
        Result.success(weather)
    } catch (ex: Exception) {
        Result.failure(ex)
    }

    suspend fun removeWeather(city: String) {
        weatherDao.deleteWeather(city)
    }

    private suspend fun fetchCity(city: String) = try {
        Result.success(weatherService.getCity(city))
    } catch (ex: Exception) {
        Result.failure(ex)
    }

    private suspend fun fetchWeather(geoId: Int) = try {
        Result.success(weatherService.getWeather(geoId))
    } catch (ex: Exception) {
        Result.failure(ex)
    }
}
