package com.pmz.rocketweather.ui.feature.weather

import androidx.lifecycle.viewModelScope
import com.pmz.rocketweather.base.BaseViewModel
import com.pmz.rocketweather.di.IoDispatcher
import com.pmz.rocketweather.model.repository.BottleWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val bottleWeatherRepo: BottleWeatherRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<WeatherContract.Event, WeatherContract.State, WeatherContract.Effect>() {

    init {
        viewModelScope.launch(dispatcher) {
            bottleWeatherRepo.weather.collect { weather ->
                withContext(Dispatchers.Main) {
                    val newState = when {
                        weather.isNotEmpty() -> WeatherContract.State.Success(weather)
                        else -> WeatherContract.State.Empty
                    }
                    setState { viewState.value.also { isLoading = false } }
                    setState { newState }
                }
            }
        }
    }

    override fun setInitialState() = WeatherContract.State.Empty

    override fun handleEvents(event: WeatherContract.Event) {
        when (event) {
            is WeatherContract.Event.AddNewCity -> fetchWeather(event.query)
            is WeatherContract.Event.DeleteCity -> removeCity(event.weather.cityName)
        }
    }

    private fun removeCity(city: String) {
        viewModelScope.launch(dispatcher) {
            bottleWeatherRepo.removeWeather(city)
        }
    }

    private fun fetchWeather(query: String = "Chelsea") {
        setState { viewState.value.apply { isLoading = true } }
        viewModelScope.launch(dispatcher) {
            val weatherResponseState = try {
                bottleWeatherRepo.getWeather(query).getOrThrow()
            } catch (ex: Exception) {
                WeatherContract.State.Failure(ex.message ?: "Something went wrong")
            }
            /*// TODO: 12/23/21 Handle Error States
            if (weatherResponseState is WeatherContract.State.Success)
                setState { weatherResponseState }*/
        }
    }
}