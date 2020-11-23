package com.usetech.x5weather.model.model

import com.usetech.x5weather.model.enums.WeatherStateEnum
import com.usetech.x5weather.model.enums.WeatherType

class DailyWeatherModel {
    var id: Long = 0
    var idCity: Long = 0
    var temp: Int = 0
    var date: Long = 0
    var timezone: String = ""
    var state: WeatherStateEnum = WeatherStateEnum.NONE
    var typeWeather: WeatherType = WeatherType.NONE
}