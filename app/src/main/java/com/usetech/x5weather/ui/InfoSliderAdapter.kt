package com.usetech.x5weather.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.usetech.x5weather.model.model.CityModel
import com.usetech.x5weather.ui.info_weather.InfoWeatherFragment

class InfoSliderAdapter(
    fragmentManager: FragmentManager,
    private val mDataSet: MutableList<CityModel> = mutableListOf()
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return InfoWeatherFragment.newInstance(mDataSet[position].id, mDataSet[position].nameCity)
    }

    override fun getCount(): Int {
        return mDataSet.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    fun setNewData(weather: List<CityModel>) {
        mDataSet.clear()
        mDataSet.addAll(weather)
        notifyDataSetChanged()
    }
}
