package com.usetech.x5weather.model.model

import com.usetech.x5weather.model.enums.WeatherStateEnum

class CityWeatherModel {
    var cityId: Long = 0
    var cityName: String = ""
    var country: String = ""
    var temp: Int = 0
    var state: WeatherStateEnum = WeatherStateEnum.NONE

}