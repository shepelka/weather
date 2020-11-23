package com.usetech.x5weather.domain

import com.usetech.x5weather.model.model.CityModel
import com.usetech.x5weather.repository.AddCityRepository
import io.reactivex.Flowable
import io.reactivex.Single

interface WeatherUseCase {
    fun findListCity(partName: String = ""): Flowable<List<CityModel>>
    fun selectCity(city: CityModel): Single<Boolean>
}

class WeatherUseCaseImpl(
    private val repository: AddCityRepository
) : WeatherUseCase {

    override fun findListCity(partName: String): Flowable<List<CityModel>> {
        return repository.searchCityListByName(partName)
    }

    override fun selectCity(city: CityModel): Single<Boolean> {
        return repository.getWeatherFromServer(city).flatMap {
            if (it) {
                return@flatMap repository.selectCityById(city.id)
            } else {
                return@flatMap Single.fromCallable {
                    return@fromCallable false
                }
            }
        }
    }
}