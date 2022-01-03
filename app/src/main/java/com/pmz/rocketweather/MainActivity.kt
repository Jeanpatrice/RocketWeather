package com.pmz.rocketweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.pmz.rocketweather.ui.feature.weather.WeatherViewModel
import com.pmz.rocketweather.ui.feature.weather.composables.CreateWeatherScreen
import com.pmz.rocketweather.ui.theme.RocketWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RocketWeatherTheme {
                ProvideWindowInsets {
                    // A surface container using the 'background' color from the theme
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "destination_weather") {
                        composable(route = "destination_weather") {
                            CreateWeatherDestination(navController)
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateWeatherDestination(navController: NavHostController) {
    val weatherViewModel: WeatherViewModel = hiltViewModel()
    val state = weatherViewModel.viewState.value
    CreateWeatherScreen(
        state = state,
        effectFlow = weatherViewModel.effect,
        onEventSent = { event -> weatherViewModel.setEvent(event) },
        onNavigationRequested = { }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RocketWeatherTheme {
        //Greeting("Android")
    }
}