package com.usetech.x5weather.ui.info_weather

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.usetech.x5weather.R
import com.usetech.x5weather.model.model.DailyWeatherModel
import com.usetech.x5weather.utils.TimeHelper
import kotlinx.android.synthetic.main.hourly_adapter.view.*

class DailyAdapter(
    private val weatherList: MutableList<DailyWeatherModel> = mutableListOf()
) : RecyclerView.Adapter<DailyAdapter.WeatherViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.daily_adapter, parent, false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val context = holder.context
        val item = weatherList[position]

        holder.tvTime.text = TimeHelper.convertTimeToDateMonthWeekFormat(item.date, item.timezone)
        holder.ivState.setImageResource(item.state.iconRes)

        if (item.temp > 0) {
            holder.tvTemp.text =
                context.getString(R.string.string_temp_format_plus, item.temp) // temp now
        } else {
            holder.tvTemp.text =
                context.getString(R.string.string_temp_format_minus, item.temp) // temp now
        }
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        val tvTime: TextView = itemView.tvTime
        val ivState: ImageView = itemView.ivState
        val tvTemp: TextView = itemView.tvTemp
    }

    fun setNewData(weather: List<DailyWeatherModel>) {
        weatherList.clear()
        weatherList.addAll(weather)
        notifyDataSetChanged()
    }
}