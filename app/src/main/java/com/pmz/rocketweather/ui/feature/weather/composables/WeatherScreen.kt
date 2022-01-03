package com.pmz.rocketweather.ui.feature.weather.composables

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.pmz.rocketweather.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.pmz.rocketweather.model.custom.WeatherView
import com.pmz.rocketweather.ui.feature.shared.ActionDelete
import com.pmz.rocketweather.ui.feature.shared.ActionRadar
import com.pmz.rocketweather.ui.feature.shared.ActionSearch
import com.pmz.rocketweather.ui.feature.shared.WeatherToolBarColors
import com.pmz.rocketweather.ui.feature.weather.WeatherContract
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@OptIn(InternalCoroutinesApi::class)
@Composable
fun CreateWeatherScreen(
    state: WeatherContract.State,
    effectFlow: Flow<WeatherContract.Effect>?,
    onEventSent: (event: WeatherContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: WeatherContract.Effect.Navigation) -> Unit
) {
    var showSearch by remember { mutableStateOf(false) }
    var currentPage by remember { mutableStateOf(0) }
    val scale = 1.2f
    val cityAdded by remember { mutableStateOf(false) }

    // Listen for side effects from the VM
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->

        }?.collect()
    }

    Column {

        Box {
            if (state is WeatherContract.State.Empty) EmptyStateScreen()
            if (state is WeatherContract.State.Success) WeatherScreen(
                weatherViews = state.data,
                pageIndexChange = { currentPage = it },
                cityAdded,
            )

            Column {
                Spacer(
                    Modifier
                        .statusBarsHeight()
                        .fillMaxWidth()
                )
                MediumTopAppBar(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    title = { },
                    navigationIcon = { ActionSearch(Modifier.clickable { showSearch = true }) },
                    actions = {
                        if (state is WeatherContract.State.Success) {
                            ActionDelete(
                                Modifier
                                    .scale(scale)
                                    .clickable {
                                        onEventSent(WeatherContract.Event.DeleteCity(state.data[currentPage]))
                                    }
                            )
                            ActionRadar(
                                Modifier
                                    .padding(start = 16.dp)
                                    .scale(scale)
                            )
                        }
                    },
                    colors = WeatherToolBarColors()
                )
            }
        }
        Spacer(
            Modifier
                .navigationBarsHeight()
                .fillMaxWidth()
        )
    }


    if (showSearch) SearchScreen(
        close = { showSearch = false },
        query = {
            showSearch = false
            onEventSent(WeatherContract.Event.AddNewCity(it))
            "Query is $it".logMe()
        }
    )

    if (state.isLoading) LoadingScreen()
}

@ExperimentalFoundationApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun WeatherScreen(
    weatherViews: List<WeatherView>,
    pageIndexChange: (Int) -> Unit,
    cityAdded: Boolean
) {
    WeatherContent(weatherViews, pageIndexChange, cityAdded)
}


fun String.logMe() {
    Log.d("RocketWeatherLog", this)
}




