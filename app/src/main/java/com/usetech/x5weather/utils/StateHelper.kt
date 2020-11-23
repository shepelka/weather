package com.usetech.x5weather.utils

import com.usetech.x5weather.model.enums.WeatherStateEnum

object StateHelper {
    fun getStateFromIdWeather(id: Long): WeatherStateEnum {
        return when (id) {
            800L -> WeatherStateEnum.CLEAR_SKY
            801L -> WeatherStateEnum.FEW_CLOUDS
            802L -> WeatherStateEnum.SCATTERED_CLOUDS
            803L, 804L -> WeatherStateEnum.BROKEN_CLOUDS
            313L, 314L, 321L, 500L, 501L, 502L, 503L, 504L, 520L, 521L, 522L, 531L -> WeatherStateEnum.SHOWER_RAIN
            300L, 301L, 302L, 310L, 311L, 312L -> WeatherStateEnum.RAIN
            200L, 201L, 202L, 210L, 211L, 212L, 221L, 230L, 231L, 232L -> WeatherStateEnum.THUNDERSTORM
            600L, 601L, 602L, 611L, 612L, 613L, 615L, 616L, 620L, 621L, 622L, 511L -> WeatherStateEnum.SNOW
            701L, 711L, 721L, 731L, 741L, 751L, 761L, 762L, 771L, 781L -> WeatherStateEnum.MIST

            else -> WeatherStateEnum.NONE
        }


    }
}