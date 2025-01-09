package com.tau.weatherapp.network

import com.tau.weatherapp.data.CurrentWeather
import com.tau.weatherapp.data.ForecastWeather
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("weather?lat=60.299727&lon=25.05390&appid=83644510d7f6d314a599f083be13dc06")
    suspend fun getCurrentWeather(@Url endUrl:String): CurrentWeather

    @GET("forecast?lat=60.299727&lon=25.05390&appid=83644510d7f6d314a599f083be13dc06")
    suspend fun getForecastWeather(@Url endUrl: String): ForecastWeather
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}