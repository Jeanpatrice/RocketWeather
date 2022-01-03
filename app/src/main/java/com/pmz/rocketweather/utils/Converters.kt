package com.pmz.rocketweather.utils

import androidx.room.TypeConverter
import com.pmz.rocketweather.model.response.Day
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {

    private val dayAdapter by lazy { Moshi.Builder().build().adapter(Day::class.java) }

    private val dayListAdapter by lazy {
        val type = Types.newParameterizedType(List::class.java, Day::class.java)
        Moshi.Builder().build().adapter<List<Day>>(type)
    }

    @TypeConverter
    fun fromDayJson(daysJson: String?) = daysJson?.run { dayAdapter.fromJson(this) }

    @TypeConverter
    fun dayToJson(days: Day) = dayAdapter.toJson(days)

    @TypeConverter
    fun fromDayListJson(
        daysJson: String?
    ) = daysJson?.run { dayListAdapter.fromJson(this) } ?: listOf()

    @TypeConverter
    fun dayListToJson(days: List<Day>) = dayListAdapter.toJson(days)
}