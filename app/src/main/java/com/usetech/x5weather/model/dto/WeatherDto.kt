package com.usetech.x5weather.model.dto

import com.google.gson.annotations.SerializedName

class WeatherDto {
    @SerializedName("id")
    var id: Long? = null

    @SerializedName("main")
    var main: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("icon")
    var icon: String? = null

}