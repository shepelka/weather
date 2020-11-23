package com.usetech.x5weather.model.model

class FullWeatherModel {
    var currentWeather: CurrentWeatherModel? = null

    var hourlyWeather: List<DailyWeatherModel>? = null

    var dailyWeather: List<DailyWeatherModel>? = null
}