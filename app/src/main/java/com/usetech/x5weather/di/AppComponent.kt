package com.usetech.x5weather.di

import com.usetech.x5weather.di.city.CityComponent
import com.usetech.x5weather.di.city.CityModule
import com.usetech.x5weather.di.info_weather.InfoWeatherComponent
import com.usetech.x5weather.di.info_weather.InfoWeatherModule
import com.usetech.x5weather.di.main.MainComponent
import com.usetech.x5weather.di.main.MainModule
import com.usetech.x5weather.di.splash.SplashComponent
import com.usetech.x5weather.di.splash.SplashModule
import com.usetech.x5weather.di.weather.WeatherComponent
import com.usetech.x5weather.di.weather.WeatherModule
import dagger.Component

@PerApplication
@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    fun plus(mainModule: MainModule): MainComponent
    fun plus(infoModule: InfoWeatherModule): InfoWeatherComponent
    fun plus(weatherModule: WeatherModule): WeatherComponent
    fun plus(cityModule: CityModule): CityComponent
    fun plus(splashModule: SplashModule): SplashComponent
}