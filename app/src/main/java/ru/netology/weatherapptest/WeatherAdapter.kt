package ru.netology.weatherapptest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var forecastDays: List<ForecastDay> = emptyList()

    fun setData(data: List<ForecastDay>) {
        forecastDays = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(forecastDays[position])
    }

    override fun getItemCount(): Int = forecastDays.size

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val conditionTextView: TextView = itemView.findViewById(R.id.conditionTextView)
        private val temperatureTextView: TextView = itemView.findViewById(R.id.temperatureTextView)
        private val windTextView: TextView = itemView.findViewById(R.id.windTextView)
        private val humidityTextView: TextView = itemView.findViewById(R.id.humidityTextView)
        private val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)

        fun bind(forecastDay: ForecastDay) {
            val day = forecastDay.day

            dateTextView.text = forecastDay.date
            conditionTextView.text = day.condition.text
            temperatureTextView.text = "${day.avgtemp_c} °C"
            windTextView.text = "${day.maxwind_kph} km/h"
            humidityTextView.text = "${day.avghumidity}%"

            Glide.with(itemView.context)
                .load("https:${day.condition.icon}")
                .into(iconImageView)
        }
    }
}
