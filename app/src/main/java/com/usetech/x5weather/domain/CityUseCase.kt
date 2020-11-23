package com.usetech.x5weather.domain

import com.usetech.x5weather.model.model.CityModel
import com.usetech.x5weather.model.model.CityWeatherModel
import com.usetech.x5weather.repository.InfoWeatherRepository
import io.reactivex.Single

interface CityUseCase {
    fun getCitySelected(): Single<List<CityModel>>
    fun getCityWithWeather(): Single<List<CityWeatherModel>>
    fun removeFromCityList(cityId: Long): Single<Int>
}

class CityUseCaseImpl(
    private val repository: InfoWeatherRepository
) : CityUseCase {

    override fun getCitySelected(): Single<List<CityModel>> {
        return repository.getCityListSelected()
    }

    override fun getCityWithWeather(): Single<List<CityWeatherModel>> {
        return repository.getCityWithWeather()
    }

    override fun removeFromCityList(cityId: Long): Single<Int> {
        return repository.removeFromCityList(cityId)
    }
}