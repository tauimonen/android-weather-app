package com.tau.weatherapp.pages

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tau.weatherapp.data.CurrentWeather
import com.tau.weatherapp.data.ForecastWeather
import com.tau.weatherapp.data.WeatherRepository
import com.tau.weatherapp.data.WeatherRepositoryImpl
import kotlinx.coroutines.async
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class WeatherHomeViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()
    var uiState: WeatherHomeUiState by mutableStateOf(WeatherHomeUiState.Loading)

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        uiState = WeatherHomeUiState.Error
    }

    fun getWeatherData() {
        viewModelScope.launch(exceptionHandler) {
            uiState =try {
                val currentWeather = async { getCurrentWeatherData() }.await()
                val forecastWeather = async { getForecastData() }.await()
                //Log.d("WeatherHomeViewModel", "Current Weather: ${currentWeather.main!!.temp}")
                //Log.d("WeatherHomeViewModel", "Current Weather: ${forecastWeather.list!!.size}")
                WeatherHomeUiState.Success(Weather(currentWeather, forecastWeather))
            } catch (e: Exception) {
                WeatherHomeUiState.Error
            }
        }
    }

    private suspend fun getCurrentWeatherData() : CurrentWeather {
        val endUrl = "weather?lat=60.299727&lon=25.05390&appid=83644510d7f6d314a599f083be13dc06"
        return weatherRepository.getCurrentWeather(endUrl)
    }

    private suspend fun getForecastData() : ForecastWeather {
        val endUrl = "forecast?lat=60.299727&lon=25.05390&appid=83644510d7f6d314a599f083be13dc06"
        return weatherRepository.getForecastWeather(endUrl)
    }
}