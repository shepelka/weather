package com.usetech.x5weather.model.dto.one_call

import com.google.gson.annotations.SerializedName

class TempDailyDto {
    @SerializedName("day")
    var tempDay: Float? = null
}