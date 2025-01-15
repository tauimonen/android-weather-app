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
import com.tau.weatherapp.data.ConnectivityRepository
import com.tau.weatherapp.utils.WEATHER_API_KEY
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherHomeViewModel(
    private val connectivityRepository: ConnectivityRepository
) : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()
    var uiState: WeatherHomeUiState by mutableStateOf(WeatherHomeUiState.Loading)

    private var latitude = 0.0
    private var longitude = 0.0

    // Observe the connectivity state
    val connectivityState: StateFlow<ConnectivityState> = connectivityRepository.connectivityState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        uiState = WeatherHomeUiState.Error
    }

    fun setLocation(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
    }

    fun getWeatherData() {
        viewModelScope.launch(exceptionHandler) {
            uiState =try {
                val currentWeather = async { getCurrentWeatherData() }.await()
                val forecastWeather = async { getForecastData() }.await()
                WeatherHomeUiState.Success(Weather(currentWeather, forecastWeather))
            } catch (e: Exception) {
                Log.e("WeatherHomeViewModel", "Exception: ${e.message}")
                WeatherHomeUiState.Error
            }
        }
    }

    private suspend fun getCurrentWeatherData() : CurrentWeather {
        val endUrl = "weather?lat=$latitude&lon=$longitude&appid=$WEATHER_API_KEY&units=metric"
        return weatherRepository.getCurrentWeather(endUrl)
    }

    private suspend fun getForecastData() : ForecastWeather {
        val endUrl = "forecast?lat=$latitude&lon=$longitude&appid=$WEATHER_API_KEY&units=metric"
        return weatherRepository.getForecastWeather(endUrl)
    }
}