package ru.netology.weatherapptest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherData(
    val forecast: Forecast
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day
)

data class Day(
    val condition: Condition,
    val avgtemp_c: Double,
    val maxwind_kph: Double,
    val avghumidity: Double
)

data class Condition(
    val text: String,
    val icon: String
)

interface WeatherApiService {
    @GET("v1/forecast.json")
    fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int
    ): Call<WeatherData>
}

