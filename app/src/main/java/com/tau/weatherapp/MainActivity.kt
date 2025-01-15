package com.tau.weatherapp

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tau.weatherapp.pages.ConnectivityState
import com.tau.weatherapp.pages.WeatherHomeScreen
import com.tau.weatherapp.pages.WeatherHomeViewModel
import com.tau.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client: FusedLocationProviderClient = LocationServices
            .getFusedLocationProviderClient(this)
        enableEdgeToEdge()
        setContent {
            WeatherApp(client)
        }
    }
}

@Composable
fun WeatherApp(
    client: FusedLocationProviderClient,
    modifier: Modifier = Modifier
) {
    val weatherHomeViewModel: WeatherHomeViewModel = viewModel(factory = WeatherHomeViewModel.Factory)
    val context = LocalContext.current
    var locationPermissionGranted by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { granted ->
        locationPermissionGranted = granted
    }
    LaunchedEffect(Unit) {
        val isPermissionGranted = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if(!isPermissionGranted) {
            launcher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            locationPermissionGranted = true
        }
    }
    LaunchedEffect(locationPermissionGranted) {
        if (locationPermissionGranted) {
            client.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    weatherHomeViewModel.setLocation(location.latitude, location.longitude)
                    weatherHomeViewModel.getWeatherData()
                }
            }
        }
    }

    val connectivityState by weatherHomeViewModel.connectivityState.collectAsState()

    WeatherAppTheme {
        WeatherHomeScreen(
            isConnected = connectivityState == ConnectivityState.Available,
            weatherHomeViewModel.uiState
        )
    }
}


