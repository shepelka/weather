package com.usetech.x5weather.data.database.converter

import androidx.room.TypeConverter
import com.usetech.x5weather.model.enums.WeatherType

class WeatherTypeConverter {
    @TypeConverter
    fun fromEnum(item: WeatherType): String {
        return item.name
    }

    @TypeConverter
    fun toWeatherType(itemInt: String): WeatherType {
        return try {
            WeatherType.valueOf(itemInt)
        } catch (e: Exception) {
            WeatherType.NONE
        }
    }
}