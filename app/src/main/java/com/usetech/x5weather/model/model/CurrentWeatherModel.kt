package com.usetech.x5weather.model.model

import com.usetech.x5weather.model.enums.WeatherStateEnum
import com.usetech.x5weather.model.enums.WeatherType

class CurrentWeatherModel {
    var id: Long = 0
    var idCity: Long = 0
    var nameCity: String = ""
    var temp: Int = 0
    var state: WeatherStateEnum = WeatherStateEnum.NONE
    var pressure: Int = 0
    var humidity: Int = 0
    var windSpeed: Int = 0
    var windDegrees: Int = 0
    var typeWeather: WeatherType = WeatherType.NONE
    var date: Long = 0
    var timezone: String = ""
}