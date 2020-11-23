package com.usetech.x5weather.domain

import com.usetech.x5weather.model.model.CurrentWeatherModel
import com.usetech.x5weather.model.model.FullWeatherModel
import com.usetech.x5weather.repository.InfoWeatherRepository
import io.reactivex.Flowable
import io.reactivex.Single

interface InfoWeatherUseCase {
    fun getWeatherDatabaseByCityId(id: Long): Single<List<CurrentWeatherModel>>

    fun getFullWeatherFromServer(idCode: Long): Single<FullWeatherModel>
}

class InfoWeatherUseCaseImpl(
    private val repository: InfoWeatherRepository
) : InfoWeatherUseCase {

    override fun getFullWeatherFromServer(idCode: Long): Single<FullWeatherModel> {
        return repository.getFullWeatherByCityId(idCode)
    }

    override fun getWeatherDatabaseByCityId(id: Long): Single<List<CurrentWeatherModel>> {
        return repository.getWeatherDatabaseByCityId(id)
    }

}