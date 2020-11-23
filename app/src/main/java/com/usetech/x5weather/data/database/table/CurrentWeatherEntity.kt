package com.usetech.x5weather.data.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.usetech.x5weather.data.database.converter.WeatherStateConverter
import com.usetech.x5weather.data.database.converter.WeatherTypeConverter
import com.usetech.x5weather.model.enums.WeatherStateEnum
import com.usetech.x5weather.model.enums.WeatherType


@Entity(tableName = "current_weather")
open class CurrentWeatherEntity : BaseEntity() {
    @ColumnInfo(name = "id_city")
    var idCity: Long = 0

    @ColumnInfo(name = "temp")
    var temp: Int = 0

    @TypeConverters(value = [WeatherStateConverter::class])
    @ColumnInfo(name = "state")
    var state: WeatherStateEnum = WeatherStateEnum.NONE

    @ColumnInfo(name = "pressure")
    var pressure: Int? = null

    @ColumnInfo(name = "humidity")
    var humidity: Int? = null

    @ColumnInfo(name = "wind_speed")
    var windSpeed: Int? = null

    @ColumnInfo(name = "wind_degrees")
    var windDegrees: Int? = null

    @TypeConverters(value = [WeatherTypeConverter::class])
    @ColumnInfo(name = "type_weather")
    var typeWeather: WeatherType = WeatherType.NONE

    @ColumnInfo(name = "date")
    var date: Long = 0

    @ColumnInfo(name = "timezone")
    var timezone: String = ""

}