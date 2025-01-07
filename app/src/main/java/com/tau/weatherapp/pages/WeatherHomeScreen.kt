package com.tau.weatherapp.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tau.weatherapp.R
import com.tau.weatherapp.customuis.AppBackground

@Composable
fun WeatherHomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AppBackground(photoId = R.drawable.rain)

    }
}

@Preview
@Composable
fun PreviewWeatherHomeScreen () {
    WeatherHomeScreen()
}