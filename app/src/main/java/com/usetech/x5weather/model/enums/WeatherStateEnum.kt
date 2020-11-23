package com.usetech.x5weather.model.enums

import com.usetech.x5weather.R

enum class WeatherStateEnum(
    val description: Int,
    val iconRes: Int,
    val iconResBlack: Int
) {
    CLEAR_SKY(R.string.clear_sky, R.drawable.ic_clear_sky, R.drawable.ic_clear_sky_black),
    FEW_CLOUDS(R.string.few_clouds, R.drawable.ic_few_clouds, R.drawable.ic_few_clouds_black),
    SCATTERED_CLOUDS(
        R.string.scattered_clouds,
        R.drawable.ic_scattered_clouds,
        R.drawable.ic_scattered_clouds_black
    ),
    BROKEN_CLOUDS(
        R.string.broken_clouds,
        R.drawable.ic_broken_clouds,
        R.drawable.ic_broken_clouds_black
    ),
    SHOWER_RAIN(R.string.shower_rain, R.drawable.ic_shower_rain, R.drawable.ic_shower_rain_black),
    RAIN(R.string.rain, R.drawable.ic_rain, R.drawable.ic_rain_black),
    THUNDERSTORM(
        R.string.thunderstorm,
        R.drawable.ic_thunderstorm,
        R.drawable.ic_thunderstorm_black
    ),
    SNOW(R.string.snow, R.drawable.ic_snow, R.drawable.ic_snow_black),
    MIST(R.string.mist, R.drawable.ic_mist, R.drawable.ic_mist_black),
    NONE(R.string.common_not_set, R.drawable.ic_default_state, R.drawable.ic_default_state)
}