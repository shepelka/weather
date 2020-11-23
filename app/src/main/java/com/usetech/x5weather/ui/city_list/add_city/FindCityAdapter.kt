package com.usetech.x5weather.ui.city_list.add_city

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.usetech.x5weather.R
import com.usetech.x5weather.model.model.CityModel
import kotlinx.android.synthetic.main.city_list_adapter.view.*

class FindCityAdapter(
    private val cityList: MutableList<CityModel> = mutableListOf(),
    val selectedCity: (city: CityModel) -> Unit
) : RecyclerView.Adapter<FindCityAdapter.FindCityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindCityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.find_city_adapter, parent, false)
        return FindCityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: FindCityViewHolder, position: Int) {
        val context = holder.context
        val item = cityList[position]
        holder.tvCity.text = context.getString(
            R.string.common_two_strings_format_with_deliver,
            item.nameCity,
            item.country
        )

        holder.rlMain.setOnClickListener {
            selectedCity.invoke(item)
        }
    }


    inner class FindCityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        val tvCity: TextView = itemView.tvCity
        val rlMain: RelativeLayout = itemView.rlMain
    }

    fun setNewData(city: List<CityModel>) {
        cityList.clear()
        cityList.addAll(city)
        notifyDataSetChanged()
    }


}