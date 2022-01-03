package com.pmz.rocketweather.ui.feature.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.pmz.rocketweather.R

@Composable
fun WeatherToolBarColors() = TopAppBarDefaults.mediumTopAppBarColors(
    actionIconContentColor = Color.White,
    containerColor = Color.Transparent,
    navigationIconContentColor = Color.White,
    scrolledContainerColor = Color.White,
)

@Composable
fun ActionClose(modifier: Modifier) = Icon(
    modifier = modifier,
    contentDescription = "Search",
    painter = painterResource(id = R.drawable.ic_icon_close),
    tint = Color.Black
)

@Composable
fun ActionSearch(modifier: Modifier) = Icon(
    modifier = modifier,
    contentDescription = "Search",
    painter = painterResource(id = R.drawable.ic_icon_search),
    tint = Color.White
)

@Composable
fun ActionDelete(modifier: Modifier) = Icon(
    modifier = modifier,
    contentDescription = "Delete",
    imageVector = Icons.Default.Delete,
    tint = Color.White
)

@Composable
fun ActionRadar(modifier: Modifier) = Icon(
    modifier = modifier,
    contentDescription = "Radar",
    painter = painterResource(id = R.drawable.ic_icon_radar),
    tint = Color.White
)