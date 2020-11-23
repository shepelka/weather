package com.usetech.x5weather.ui.city_list.add_city

import com.usetech.x5weather.base.BasePresenter
import com.usetech.x5weather.base.BaseView
import com.usetech.x5weather.model.model.CityModel

interface WeatherView : BaseView {
    fun setListCity(city: List<CityModel>)
    fun showNotFoundSearchResult(searchString: String)

    fun setErrorSelected()
    fun successSelectedGoToList()

    fun showProgress(isLoading: Boolean)
}

abstract class AddCityPresenter : BasePresenter<WeatherView>() {
    abstract fun findCity(partName: String = "")
    abstract fun selectCity(city: CityModel)
}