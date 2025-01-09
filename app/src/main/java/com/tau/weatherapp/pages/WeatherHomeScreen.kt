package com.tau.weatherapp.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tau.weatherapp.R
import com.tau.weatherapp.customuis.AppBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherHomeScreen(
    uiState: WeatherHomeUiState,
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AppBackground(photoId = R.drawable.rain)
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Weather App", style = MaterialTheme.typography.titleLarge) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        actionIconContentColor = Color.White,
                    )
                )
            },
            containerColor = Color.Transparent
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier.padding(it).fillMaxSize().wrapContentSize()
            ) {
                when (uiState) {
                    is WeatherHomeUiState.Error -> {
                        Text("Failed to load data")
                    }

                    is WeatherHomeUiState.Loading -> {
                        Text("Loading..")
                    }

                    is WeatherHomeUiState.Success -> {
                        Text(uiState.weather.currentWeather.main!!.temp.toString(),
                            style = MaterialTheme.typography.displayLarge)
                    }
                }
            }
        }
    }
}
