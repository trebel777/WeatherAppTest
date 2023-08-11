package ru.netology.weatherapptest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.netology.weatherapptest.BuildConfig


class MainActivity : AppCompatActivity() {

    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        weatherAdapter = WeatherAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = weatherAdapter


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApiService = retrofit.create(WeatherApiService::class.java)


        val apiKey = BuildConfig.WEATHER_API_KEY
        val location = "Saint Petersburg"
        val days = 5

        weatherApiService.getWeatherForecast(apiKey, location, days)
            .enqueue(object : Callback<WeatherData> {
                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    if (response.isSuccessful) {
                        val forecastDays = response.body()?.forecast?.forecastday ?: emptyList()
                        weatherAdapter.setData(forecastDays)
                    } else {
                        showErrorToast()
                    }
                }

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    showErrorToast()
                }
            })
    }

    private fun showErrorToast() {
        Toast.makeText(this, "Failed to fetch weather data", Toast.LENGTH_SHORT).show()
    }
}
