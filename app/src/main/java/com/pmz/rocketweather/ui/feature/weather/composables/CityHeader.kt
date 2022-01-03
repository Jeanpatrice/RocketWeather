package com.pmz.rocketweather.ui.feature.weather.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.pmz.rocketweather.R
import com.pmz.rocketweather.model.custom.WeatherView

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun CityHeader(
    weatherViewList: List<WeatherView>,
    currentWeatherView: WeatherView,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        CityImageBackGround(currentWeatherView)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color.Black, Color.Black)), alpha = .2f)
        )
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(Modifier.padding(6.dp)) {
                weatherViewList.forEach { Dot(isSelected = it == currentWeatherView) }
            }

            Text(
                modifier = Modifier.padding(6.dp),
                text = currentWeatherView.cityName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                modifier = Modifier.padding(6.dp),
                text = "${currentWeatherView.date} ${currentWeatherView.time}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = Modifier.padding(6.dp),
                text = currentWeatherView.temp,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }

}

@ExperimentalPagerApi
@Composable
private fun CityImageBackGround(weatherView: WeatherView) {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .focusable(false),
        painter = rememberImagePainter(
            data = weatherView.image,
            onExecute = { _, _ -> true },
            builder = {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
            }
        ),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun Dot(isSelected: Boolean = false) = Spacer(
    Modifier
        .padding(4.dp)
        .size(8.dp)
        .background(
            color = if (isSelected) Color.White else Color(255, 255, 255, 0x7A),
            shape = CircleShape
        )
)

