package com.pmz.rocketweather.ui.feature.weather

import com.pmz.rocketweather.base.ViewEvent
import com.pmz.rocketweather.base.ViewSideEffect
import com.pmz.rocketweather.base.ViewState
import com.pmz.rocketweather.model.custom.WeatherView

class WeatherContract {
    sealed class Event : ViewEvent {
        data class AddNewCity(val query: String) : Event()
        data class DeleteCity(val weather: WeatherView) : Event()
    }

    sealed class State(var isLoading: Boolean = false) : ViewState {
        data class Success(val data: List<WeatherView>) : State()
        data class Failure(val errorMsg: String) : State()
        object Empty : State()

    }

    sealed class Effect : ViewSideEffect {

        sealed class Navigation : Effect() {
        }
    }
}