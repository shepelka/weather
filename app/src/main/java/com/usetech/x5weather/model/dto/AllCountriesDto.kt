package com.usetech.x5weather.model.dto

import com.google.gson.annotations.SerializedName

class AllCountriesDto {
    @SerializedName("name")
    var nameCity: String? = null

    @SerializedName("country_name")
    var country: String? = null

    @SerializedName("coord")
    var coordinate: Coordinate? = null
}

class Coordinate {
    @SerializedName("lat")
    var latitude: Float? = 0f

    @SerializedName("lon")
    var longitude: Float? = 0f
}