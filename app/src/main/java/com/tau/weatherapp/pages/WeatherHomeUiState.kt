package com.tau.weatherapp.pages

import com.tau.weatherapp.data.CurrentWeather
import com.tau.weatherapp.data.ForecastWeather

data class Weather(
    val currentWeather: CurrentWeather,
    val forecastWeather: ForecastWeather
)

sealed interface WeatherHomeUiState {
    data class Success(val weather: Weather) : WeatherHomeUiState
    data object Error : WeatherHomeUiState
    data object Loading : WeatherHomeUiState
}

sealed interface ConnectivityState {
    object Available : ConnectivityState
    object Unavailable : ConnectivityState
}