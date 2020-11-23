package com.usetech.x5weather.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.TypeConverters
import com.usetech.x5weather.data.database.converter.WeatherTypeConverter
import com.usetech.x5weather.data.database.table.CurrentWeatherEntity
import com.usetech.x5weather.data.database.table.CurrentWeatherWithCity
import com.usetech.x5weather.model.enums.WeatherType
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class CurrentWeatherDao : AbstractDao<CurrentWeatherEntity>() {

    @Query("SELECT * FROM current_weather WHERE id_city =:idCity ORDER BY created_time DESC LIMIT 1")
    abstract fun getLastCurrentWeather(idCity: Long): Flowable<List<CurrentWeatherEntity>>

    @Query("SELECT * FROM current_weather WHERE id_city =:idCity")
    abstract fun getWeatherById(idCity: Long): Single<List<CurrentWeatherEntity>>

    @Query("SELECT * FROM current_weather WHERE id_city =:idCity AND type_weather=:type")
    @TypeConverters(value = [WeatherTypeConverter::class])
    abstract fun getCurrentWeather(
        idCity: Long,
        type: WeatherType = WeatherType.CURRENT
    ): CurrentWeatherEntity?

    @Query("DELETE FROM current_weather WHERE id_city =:idCity AND type_weather=:type")
    @TypeConverters(value = [WeatherTypeConverter::class])
    abstract fun deleteAllByTypeAndCityId(
        idCity: Long,
        type: WeatherType
    )

    @Query("DELETE FROM current_weather WHERE id_city =:idCity")
    abstract fun deleteAllByCityId(idCity: Long)

    @Query("SELECT * FROM current_weather AS w JOIN city AS c ON w.id_city = c.id WHERE c.is_default = 1 AND type_weather = :type ORDER BY c.name DESC")
    @TypeConverters(value = [WeatherTypeConverter::class])
    abstract fun getCurrentWeatherWithDefaultCity(type: WeatherType = WeatherType.CURRENT): Single<MutableList<CurrentWeatherWithCity>>

    @Query("SELECT * FROM current_weather AS w JOIN city AS c ON w.id_city = c.id WHERE c.is_selected = 1 AND type_weather = :type AND c.is_default = 0 ORDER BY c.updated_time ASC")
    @TypeConverters(value = [WeatherTypeConverter::class])
    abstract fun getCurrentWeatherWithCityNoDefault(type: WeatherType = WeatherType.CURRENT): Single<List<CurrentWeatherWithCity>>
}

