package com.tau.weatherapp.network

import com.tau.weatherapp.data.CurrentWeather
import com.tau.weatherapp.data.ForecastWeather
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherApiService {
    @GET()
    suspend fun getCurrentWeather(@Url endUrl: String): CurrentWeather

    @GET()
    suspend fun getForecastWeather(@Url endUrl: String): ForecastWeather
}
