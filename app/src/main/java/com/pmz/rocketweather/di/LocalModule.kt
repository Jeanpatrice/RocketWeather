package com.pmz.rocketweather.di

import android.content.Context
import com.pmz.rocketweather.model.local.AppDatabase
import com.pmz.rocketweather.model.local.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun providesWeatherDb(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    fun provideWeatherDao(
        appDatabase: AppDatabase
    ): WeatherDao = appDatabase.weatherDao()
}