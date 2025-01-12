package com.tau.weatherapp.pages

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tau.weatherapp.R
import com.tau.weatherapp.customuis.AppBackground
import com.tau.weatherapp.data.CurrentWeather
import com.tau.weatherapp.utils.getFormattedDate
import com.tau.weatherapp.utils.getIconUrl

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
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .wrapContentSize()
            ) {
                when (uiState) {
                    is WeatherHomeUiState.Error -> Text("Failed to load data")

                    is WeatherHomeUiState.Loading -> Text("Loading..")

                    is WeatherHomeUiState.Success -> WeatherSection(weather = uiState.weather)
                }
            }
        }
    }
}

@Composable
fun WeatherSection(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        CurrentWeatherSection(
            currentWeather = weather.currentWeather,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun CurrentWeatherSection(
    currentWeather: CurrentWeather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${currentWeather.name}, ${currentWeather.sys?.country}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            // Turn to non-nullable type with !! operator
            text = getFormattedDate(currentWeather.dt!!, pattern = "MMM dd yyyy"),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            // Convert Kelvin to Celsius by subtracting 273.15
            text = "${currentWeather.main?.temp?.minus(273.15)?.toInt()}°C",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            // Convert Kelvin to Celsius by subtracting 273.15
            text = "feels like ${currentWeather.main?.feelsLike?.minus(273.15)?.toInt()}",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Log.d("WeatherHomeScreen", "Icon: ${getIconUrl(currentWeather.weather?.get(0)?.icon!!)}")
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getIconUrl(currentWeather.weather[0]?.icon!!))
                    .listener(
                        onStart = { Log.d("Coil", "Kuvan lataus alkoi") },
                        onSuccess = { _, _ -> Log.d("Coil", "Kuvan lataus onnistui") },
                        onError = { _, throwable -> Log.e("Coil", "Virhe kuvan latauksessa: $throwable") }
                    )
                    .build(),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(40.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.error)
            )
            Text(currentWeather.weather[0]!!.description!!, style = MaterialTheme.typography.titleMedium)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text("Humidity ${currentWeather.main?.humidity}%",
                    style = MaterialTheme.typography.titleMedium
                )
                Text("Pressure ${currentWeather.main?.pressure}hPa",
                    style = MaterialTheme.typography.titleMedium
                )
                Text("Visibility ${currentWeather.visibility}m",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
            Surface(modifier = Modifier.width(2.dp).height(100.dp)) {  }
            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text("Sunrise ${getFormattedDate(currentWeather.sys?.sunrise!!, pattern = "HH:mm")}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text("Sunset ${getFormattedDate(currentWeather.sys?.sunset!!, pattern = "HH:mm")}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}