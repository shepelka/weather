package com.usetech.x5weather.data.database.converter

import androidx.room.TypeConverter
import com.usetech.x5weather.model.enums.WeatherStateEnum

class WeatherStateConverter {
    @TypeConverter
    fun fromEnum(item: WeatherStateEnum): String {
        return item.name
    }

    @TypeConverter
    fun toWeatherState(itemInt: String): WeatherStateEnum {
        return try {
            WeatherStateEnum.valueOf(itemInt)
        } catch (e: Exception) {
            WeatherStateEnum.NONE
        }
    }
}