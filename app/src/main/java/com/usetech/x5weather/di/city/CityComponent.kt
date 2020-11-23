package com.usetech.x5weather.di.city

import com.usetech.x5weather.ui.city_list.city_list.ChooseCityPresenter
import dagger.Subcomponent

@Subcomponent(modules = [CityModule::class])
@CityScope
interface CityComponent {
    fun getPresenter(): ChooseCityPresenter
}