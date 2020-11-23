package com.usetech.x5weather.model.dto.one_call

import com.usetech.x5weather.model.dto.WeatherDto
import com.google.gson.annotations.SerializedName

class DailyOneCallDto {
    @SerializedName("dt")
    var date: Long? = null

    @SerializedName("temp")
    var tempDay: TempDailyDto? = null

    @SerializedName("weather")
    var weatherInfoWithIcon: List<WeatherDto>? = null
}