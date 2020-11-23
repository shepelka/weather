package com.usetech.x5weather.model.dto.one_call

import com.google.gson.annotations.SerializedName

class OneCallWeatherDto {

    @SerializedName("timezone")
    var timezone: String = ""

    @SerializedName("current")
    var currentWeather: CurrentOneCallDto? = null

    @SerializedName("hourly")
    var hourlyWeather: List<WeatherBaseDto>? = null

    @SerializedName("daily")
    var dailyWeather: List<DailyOneCallDto>? = null


}