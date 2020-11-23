package com.usetech.x5weather.data.converters

import com.usetech.x5weather.data.database.table.CityEntity
import com.usetech.x5weather.model.dto.AllCountriesDto
import com.usetech.x5weather.model.model.CityModel

fun CityEntity.toModel(): CityModel = CityModel().also {
    it.id = id ?: 0
    it.nameCity = nameCity
    it.isSelected = isSelected
    it.latitude = latitude
    it.longitude = longitude
    it.isDefault = isDefault
    it.country = country
}

fun CityModel.toEntity(): CityEntity = CityEntity().also {
    it.id = id
    it.nameCity = nameCity
    it.isSelected = isSelected
    it.latitude = latitude
    it.longitude = longitude
    it.isDefault = isDefault
    it.country = country
}

fun AllCountriesDto.toEntity(): CityEntity = CityEntity()
    .also {
        it.nameCity = nameCity.orEmpty()
        it.isSelected = false
        it.latitude = coordinate?.latitude ?: 0f
        it.longitude = coordinate?.longitude ?: 0f
        it.isDefault = false
        it.country = country.orEmpty()
    }