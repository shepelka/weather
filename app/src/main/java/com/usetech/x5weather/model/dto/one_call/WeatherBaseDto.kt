package com.usetech.x5weather.model.dto.one_call

import com.usetech.x5weather.model.dto.WeatherDto
import com.google.gson.annotations.SerializedName

open class WeatherBaseDto {
    @SerializedName("dt")
    var date: Long? = null

    @SerializedName("temp")
    var tempCurrent: Float? = null

    @SerializedName("weather")
    var weatherInfoWithIcon: List<WeatherDto>? = null
}