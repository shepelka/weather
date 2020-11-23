package com.usetech.x5weather.di.info_weather

import com.usetech.x5weather.ui.info_weather.InfoWeatherPresenter
import dagger.Subcomponent


@Subcomponent(modules = [InfoWeatherModule::class])
@InfoWeatherScope
interface InfoWeatherComponent {
    fun getPresenter() : InfoWeatherPresenter
}