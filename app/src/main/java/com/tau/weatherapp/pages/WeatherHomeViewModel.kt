package com.tau.weatherapp.pages

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tau.weatherapp.data.CurrentWeather
import com.tau.weatherapp.data.ForecastWeather
import com.tau.weatherapp.data.WeatherRepository
import kotlinx.coroutines.async
import androidx.lifecycle.viewModelScope
import com.tau.weatherapp.data.ConnectivityRepository
import com.tau.weatherapp.utils.WEATHER_API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherHomeViewModel @Inject constructor(
    private val connectivityRepository: ConnectivityRepository,
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
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

/*    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as Application
                val connectivityManager = application.getSystemService(ConnectivityManager::class.java)
                WeatherHomeViewModel(
                    connectivityRepository = DefaultConnectivityRepository(connectivityManager)
                )
            }
        }
    }*/
}