package com.usetech.x5weather.ui.info_weather

import com.usetech.x5weather.base.BasePresenter
import com.usetech.x5weather.base.BaseView
import com.usetech.x5weather.model.model.CurrentWeatherModel
import com.usetech.x5weather.model.model.DailyWeatherModel

interface InfoWeatherView : BaseView {
    fun setWeatherInfo(weather: CurrentWeatherModel)
    fun setWeatherHourly(weather: List<DailyWeatherModel>)
    fun setWeatherDaily(weather: List<DailyWeatherModel>)
    fun showLoadError()
}
abstract class InfoWeatherPresenter : BasePresenter<InfoWeatherView>(){
    abstract fun getWeatherInfo()
    abstract fun setCity(id: Long, name: String)
    abstract fun updateFromServer()
}