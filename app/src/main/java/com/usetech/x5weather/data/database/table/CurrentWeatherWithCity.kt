package com.usetech.x5weather.data.database.table

import androidx.room.Embedded
import androidx.room.Relation

class CurrentWeatherWithCity {
    @Embedded
    var weather: CurrentWeatherEntity? = null

    @Relation(parentColumn = "id_city", entityColumn = "id", entity = CityEntity::class)
    var city: List<CityEntity>? = null
}