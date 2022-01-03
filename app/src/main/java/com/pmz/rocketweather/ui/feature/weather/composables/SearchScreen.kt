package com.pmz.rocketweather.ui.feature.weather.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.statusBarsHeight
import com.pmz.rocketweather.ui.feature.shared.ActionClose
import com.pmz.rocketweather.ui.feature.shared.WeatherToolBarColors

@Composable
fun SearchScreen(
    close: () -> Unit,
    query: (String) -> Unit,
) {
    var text by remember { mutableStateOf("villas") }
    val backgroundColor = Color.White
    Column(
        Modifier
            .fillMaxSize()
            .clickable {  }
            .background(Brush.sweepGradient(listOf(backgroundColor, backgroundColor)), alpha = .8f),
    ) {
        Spacer(modifier = Modifier.statusBarsHeight())
        MediumTopAppBar(
            title = {},
            actions = {
                ActionClose(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { close() })
            },
            colors = WeatherToolBarColors()
        )
        Spacer(modifier = Modifier.height(10.dp))
        BasicTextField(
            textStyle = TextStyle(color = Color.Black, fontSize = 28.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 35.dp, end = 35.dp, top = 59.dp),
            value = text,
            onValueChange = { text = it }
        )
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp),
            color = Color.Black
        )
        if (text.isNotEmpty())
            Button(
                onClick = { query(text) },
                modifier = Modifier
                    .padding(top = 50.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Search")
            }
    }
}