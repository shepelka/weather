package com.usetech.x5weather.ui

import com.usetech.x5weather.base.BasePresenter
import com.usetech.x5weather.base.BaseView
import com.usetech.x5weather.model.model.CityModel

interface MainView : BaseView {
    fun setCityList(cityList: List<CityModel>)
    fun setPage(page: Int)
}

abstract class MainPresenter : BasePresenter<MainView>() {
    abstract fun getCityList()
    abstract fun setPage(page: Int)
}