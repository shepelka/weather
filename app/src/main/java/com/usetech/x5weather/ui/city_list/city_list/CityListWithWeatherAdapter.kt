package com.usetech.x5weather.ui.city_list.city_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.usetech.x5weather.R
import com.usetech.x5weather.model.model.CityWeatherModel
import kotlinx.android.synthetic.main.city_list_adapter.view.*
import kotlinx.android.synthetic.main.hourly_adapter.view.ivState
import kotlinx.android.synthetic.main.hourly_adapter.view.tvTemp

class CityListWithWeatherAdapter(
    private val cityList: MutableList<CityWeatherModel> = mutableListOf(),
    val deleteCity: (id: Long) -> Unit,
    val clickByCity: (position: Int) -> Unit
) : RecyclerView.Adapter<CityListWithWeatherAdapter.CityViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_list_adapter, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val context = holder.context
        val item = cityList[position]

        holder.tvCity.text = item.cityName

        holder.ivState.setImageResource(item.state.iconResBlack)
        if (item.temp > 0) {
            holder.tvTemp.text =
                context.getString(R.string.string_temp_format_plus, item.temp) // temp now
        } else {
            holder.tvTemp.text =
                context.getString(R.string.string_temp_format_minus, item.temp) // temp now
        }

        holder.rlMain.setOnClickListener {
            clickByCity.invoke(position)
        }

    }


    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        val tvCity: TextView = itemView.tvCity
        val ivState: ImageView = itemView.ivState
        val tvTemp: TextView = itemView.tvTemp
        val rlMain: RelativeLayout = itemView.rlMain
    }

    fun deleteItem(position: Int) {
        deleteCity.invoke(cityList[position].cityId)
        cityList.removeAt(position)
        notifyDataSetChanged()
    }

    fun setNewData(city: List<CityWeatherModel>) {
        cityList.clear()
        cityList.addAll(city)
        notifyDataSetChanged()
    }
}

