package com.usetech.x5weather.model.dto.one_call

import com.google.gson.annotations.SerializedName

class CurrentOneCallDto : WeatherBaseDto() {
    @SerializedName("pressure")
    var pressure: Int? = null

    @SerializedName("humidity")
    var humidity: Int? = null

    @SerializedName("wind_speed")
    var speed: Float? = null

    @SerializedName("wind_deg")
    var degrees: Float? = null
}