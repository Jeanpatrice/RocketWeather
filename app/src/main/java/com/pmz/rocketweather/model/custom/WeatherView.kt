package com.pmz.rocketweather.model.custom

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pmz.rocketweather.model.response.CityResponse
import com.pmz.rocketweather.model.response.Day
import com.pmz.rocketweather.model.response.WeatherResponse
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "weather")
data class WeatherView(
    @PrimaryKey val cityName: String = "",
    val date: String = "",
    val time: String = "",
    val image: String = "",
    val temp: String = "",
    val days: List<Day> = listOf(),
) {

    companion object {
        fun convert(
            cityResponse: CityResponse, weatherResponse: WeatherResponse
        ) = try {
            val days = weatherResponse.weather!!.days!!
            val city = cityResponse.cities.first()
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("E dd/MM/yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("KK:mm:ss", Locale.getDefault())
            val date = dateFormat.format(calendar.time).toString()
            val time = timeFormat.format(calendar.time).toString()
            WeatherView(
                cityName = "${city.asciiname}, ${city.admin1Code}",
                date = date,
                time = time,
                temp = "${days.first().high}\u00B0",
                image = city.imageURLs.androidImageURLs?.xhdpiImageURL!!,
                days = days,
            )
        } catch (ex: Exception) {
            null
        }
    }
}