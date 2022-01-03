package com.pmz.rocketweather.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pmz.rocketweather.model.custom.WeatherView
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getWeather(): Flow<List<WeatherView>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherView)

    @Query("DELETE FROM weather WHERE cityName == :city")
    suspend fun deleteWeather(city: String)
}