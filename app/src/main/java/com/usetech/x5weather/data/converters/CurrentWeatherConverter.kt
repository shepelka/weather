package com.usetech.x5weather.data.converters

import com.usetech.x5weather.data.database.table.CurrentWeatherEntity
import com.usetech.x5weather.data.database.table.CurrentWeatherWithCity
import com.usetech.x5weather.model.dto.one_call.CurrentOneCallDto
import com.usetech.x5weather.model.dto.one_call.DailyOneCallDto
import com.usetech.x5weather.model.dto.one_call.WeatherBaseDto
import com.usetech.x5weather.model.enums.WeatherStateEnum
import com.usetech.x5weather.model.enums.WeatherType
import com.usetech.x5weather.model.model.CityWeatherModel
import com.usetech.x5weather.model.model.CurrentWeatherModel
import com.usetech.x5weather.model.model.DailyWeatherModel
import com.usetech.x5weather.model.model.FullWeatherModel
import com.usetech.x5weather.utils.StateHelper
import kotlin.math.roundToInt

fun CurrentOneCallDto.toModel(
    type: WeatherType,
    idCity: Long,
    timezone: String
): CurrentWeatherModel =
    CurrentWeatherModel().also {
        it.temp = tempCurrent?.let { temp ->
            temp.roundToInt()
        } ?: 0
        it.pressure = pressure ?: 0
        it.humidity = humidity ?: 0
        it.typeWeather = type
        weatherInfoWithIcon?.firstOrNull()?.let { dto ->
            it.state = StateHelper.getStateFromIdWeather(dto.id ?: 0)
        }
        it.windSpeed = speed?.let { wind ->
            wind.roundToInt()
        } ?: 0
        it.windDegrees = degrees?.let { wind ->
            wind.roundToInt()
        } ?: 0
        it.idCity = idCity
        it.date = date ?: 0
        it.timezone = timezone
    }

fun DailyOneCallDto.toModel(type: WeatherType, idCity: Long, timezone: String): DailyWeatherModel =
    DailyWeatherModel().also {
        it.temp = tempDay?.tempDay?.let { temp ->
            temp.roundToInt()
        } ?: 0
        it.typeWeather = type
        weatherInfoWithIcon?.firstOrNull()?.let { dto ->
            it.state = StateHelper.getStateFromIdWeather(dto.id ?: 0)
        }
        it.date = date ?: 0
        it.idCity = idCity
        it.timezone = timezone
    }

fun WeatherBaseDto.toModel(type: WeatherType, idCity: Long, timezone: String): DailyWeatherModel =
    DailyWeatherModel().also {
        it.idCity = idCity
        it.temp = tempCurrent?.let { temp ->
            temp.roundToInt()
        } ?: 0
        it.typeWeather = type
        weatherInfoWithIcon?.firstOrNull()?.let { dto ->
            it.state = StateHelper.getStateFromIdWeather(dto.id ?: 0)
        }
        it.date = date ?: 0
        it.timezone = timezone
    }

fun DailyWeatherModel.toEntity(): CurrentWeatherEntity = CurrentWeatherEntity().also {
    it.idCity = idCity
    it.temp = temp
    it.date = date
    it.timezone = timezone
    it.state = state
    it.typeWeather = typeWeather
    if (id == 0L) {
        it.id = null
    } else {
        it.id = id
    }
}

fun CurrentWeatherEntity.toModel(): CurrentWeatherModel = CurrentWeatherModel().also {
    it.id = id ?: 0
    it.idCity = idCity
    it.date = date
    it.timezone = timezone
    it.temp = temp
    it.state = state
    it.pressure = pressure ?: 0
    it.humidity = humidity ?: 0
    it.windSpeed = windSpeed ?: 0
    it.windDegrees = windDegrees ?: 0
    it.typeWeather = typeWeather
}

fun CurrentWeatherModel.toEntity(): CurrentWeatherEntity = CurrentWeatherEntity().also {
    if (id == 0L) {
        it.id = null
    } else {
        it.id = id
    }
    it.idCity = idCity
    it.date = date
    it.timezone = timezone
    it.temp = temp
    it.state = state
    it.pressure = pressure
    it.humidity = humidity
    it.windSpeed = windSpeed
    it.windDegrees = windDegrees
    it.typeWeather = typeWeather
}

fun CurrentWeatherWithCity.toModel(): CityWeatherModel = CityWeatherModel().also {
    it.cityId = city?.lastOrNull()?.id ?: 0
    it.cityName = city?.lastOrNull()?.nameCity.orEmpty()
    it.temp = weather?.temp ?: 0
    it.state = weather?.state ?: WeatherStateEnum.NONE
}

fun CurrentWeatherModel.toDailyWeatherModel(): DailyWeatherModel = DailyWeatherModel().also {
    it.id = id
    it.idCity = idCity
    it.date = date
    it.timezone = timezone
    it.temp = temp
    it.state = state
    it.typeWeather = typeWeather

}

fun List<CurrentWeatherModel>.toFullList(): FullWeatherModel {
    return FullWeatherModel().also {
        var currentModel: CurrentWeatherModel? = null
        val hourlyWeather: MutableList<DailyWeatherModel> = mutableListOf()
        val dailyWeather: MutableList<DailyWeatherModel> = mutableListOf()

        forEach { current ->
            when (current.typeWeather) {
                WeatherType.CURRENT -> {
                    currentModel = current
                }
                WeatherType.HOURLY -> {
                    hourlyWeather.add(current.toDailyWeatherModel())
                }
                WeatherType.DAILY -> {
                    dailyWeather.add(current.toDailyWeatherModel())
                }
                else -> {
                    //nothing
                }
            }
        }
        if (hourlyWeather.isEmpty()) {
            it.hourlyWeather = null
        } else {
            it.hourlyWeather = hourlyWeather
        }

        if (dailyWeather.isEmpty()) {
            it.dailyWeather = null
        } else {
            it.dailyWeather = dailyWeather
        }
        it.currentWeather = currentModel
    }
}