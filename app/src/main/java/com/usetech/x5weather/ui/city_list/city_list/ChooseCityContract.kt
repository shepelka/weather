package com.usetech.x5weather.ui.city_list.city_list

import com.usetech.x5weather.base.BasePresenter
import com.usetech.x5weather.base.BaseView
import com.usetech.x5weather.model.model.CityWeatherModel

interface ChooseCityView : BaseView {
    fun setCity(cityList: List<CityWeatherModel>)
    fun disableEnableBtnAdd(isActive: Boolean)
}

abstract class ChooseCityPresenter : BasePresenter<ChooseCityView>() {
    abstract fun getCity()
    abstract fun removeFromCityList(cityId: Long)
}