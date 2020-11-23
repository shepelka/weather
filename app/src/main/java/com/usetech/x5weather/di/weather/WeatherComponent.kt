package com.usetech.x5weather.di.weather

import com.usetech.x5weather.ui.city_list.add_city.AddCityPresenter
import dagger.Subcomponent

@Subcomponent(modules = [WeatherModule::class])
@WeatherScope
interface WeatherComponent {
    fun getPresenter(): AddCityPresenter
}