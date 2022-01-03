package com.pmz.rocketweather.ui.feature.weather.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.pmz.rocketweather.R
import com.pmz.rocketweather.model.custom.WeatherView
import com.pmz.rocketweather.model.response.Day
import kotlin.math.roundToInt

@ExperimentalFoundationApi
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun WeatherContent(
    weatherViewList: List<WeatherView>,
    pageIndexChange: (Int) -> Unit,
    cityAdded: Boolean,
) {
    val pagerState = rememberPagerState(if (cityAdded) weatherViewList.size.minus(1) else 0)

    HorizontalPager(count = weatherViewList.size, state = pagerState) { page ->
        var daySelectedIndex by remember { mutableStateOf(0) }
        val weatherView = weatherViewList[page]
        pageIndexChange(pagerState.currentPage)

        Column() {
            CityHeader(
                weatherViewList, weatherView,
                Modifier
                    .weight(.7f)
                    .fillMaxSize()
            )

            Column(
                Modifier
                    .weight(1f)
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(201, 199, 199), Color(119, 163, 205)
                            )
                        )
                    )
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    weatherView.days.forEach { day ->
                        Day(day, weatherView.days[daySelectedIndex] == day) {
                            daySelectedIndex = weatherView.days.indexOf(it)
                        }
                    }
                }
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    color = Color.White
                )

                val headerStyle = MaterialTheme.typography.labelSmall
                val bodyStyle = MaterialTheme.typography.bodyLarge
                LazyColumn {

                    val headerModifier = Modifier
                        .weight(.17f)
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                    val bodyModifier = Modifier
                        .weight(.17f)
                        .padding(8.dp)
                    item {
                        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom) {
                            TableCell(headerModifier, text = "", style = headerStyle)
                            Text(
                                modifier = headerModifier,
                                text = "Time",
                                style = headerStyle,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = headerModifier,
                                text = "Temp",
                                style = headerStyle,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = headerModifier,
                                text = "Chance of Rain",
                                style = headerStyle, textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = headerModifier,
                                text = "Wind (mph)",
                                style = headerStyle,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = headerModifier,
                                text = "Humidity",
                                style = headerStyle,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    items(weatherView.days[daySelectedIndex].hourlyWeather) { hourly ->
                        Row(
                            Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            Icon(
                                painter = hourly.weatherType.weatherTypeIcon(),
                                modifier = bodyModifier,
                                contentDescription = "Weather",
                                tint = Color.White
                            )
                            TableCell(
                                bodyModifier,
                                text = hourly.hour.toHourString(),
                                style = bodyStyle
                            )
                            TableCell(
                                bodyModifier,
                                text = "${hourly.temperature}\u00B0",
                                style = bodyStyle
                            )
                            TableCell(
                                bodyModifier,
                                text = "${hourly.rainChance.times(100).roundToInt()}%",
                                style = bodyStyle
                            )
                            TableCell(
                                bodyModifier,
                                text = hourly.windSpeed.toString(),
                                style = bodyStyle
                            )
                            TableCell(
                                bodyModifier,
                                text = "${hourly.humidity.times(100).roundToInt()}%",
                                style = bodyStyle
                            )
                        }
                    }

                }
            }
        }

    }
}

@Composable
private fun Day(
    day: Day,
    isSelected: Boolean = false,
    daySelected: (Day) -> Unit = {},
) {
    val color = if (isSelected) Color.White else Color(101, 127, 157)
    Column(
        Modifier
            .padding(10.dp)
            .clickable { daySelected(day) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = day.dayOfTheWeek.toDayString(),
            color = color,
            style = TextStyle(fontSize = 18.sp)
        )
        Icon(
            modifier = Modifier.padding(top = 8.dp, bottom = 6.dp),
            painter = day.weatherType.weatherTypeIcon(),
            contentDescription = day.weatherType,
            tint = color
        )
        Text(text = "${day.high}\u00B0", color = color, style = TextStyle(fontSize = 14.sp))
    }
}

@Composable
fun TableCell(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    align: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = align,
        maxLines = 2,
        style = style
    )
}

@Composable
private fun Int?.toDayString() = when (this) {
    0 -> stringResource(R.string.mon)
    1 -> stringResource(R.string.tue)
    2 -> stringResource(R.string.wed)
    3 -> stringResource(R.string.thu)
    4 -> stringResource(R.string.fri)
    5 -> stringResource(R.string.sat)
    6 -> stringResource(R.string.sun)
    else -> ""
}

@Composable
private fun Int.toHourString() = when {
    this == 0 -> "12PM"
    this in 1..12 -> "${this}PM"
    this in 13..23 -> "${this.minus(12)}AM"
    else -> ""
}

@Composable
private fun String?.weatherTypeIcon() = when (this) {
    "partlyCloudy" -> painterResource(R.drawable.ic_icon_weather_active_ic_partly_cloudy_active)
    "cloudy" -> painterResource(R.drawable.ic_icon_weather_active_ic_cloudy_active)
    "lightRain" -> painterResource(R.drawable.ic_icon_weather_active_ic_light_rain_active)
    "heavyRain" -> painterResource(R.drawable.ic_icon_weather_active_ic_heavy_rain_active)
    "sunny" -> painterResource(R.drawable.ic_icon_weather_active_ic_sunny_active)
    "snowSleet" -> painterResource(R.drawable.ic_icon_weather_active_ic_snow_sleet_active)
    else -> painterResource(id = R.drawable.ic_launcher_foreground)
}
