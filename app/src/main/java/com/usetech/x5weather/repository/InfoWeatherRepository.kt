package com.usetech.x5weather.repository

import android.database.sqlite.SQLiteException
import com.usetech.x5weather.data.converters.toEntity
import com.usetech.x5weather.data.converters.toModel
import com.usetech.x5weather.data.database.dao.CityDao
import com.usetech.x5weather.data.database.dao.CurrentWeatherDao
import com.usetech.x5weather.model.dto.one_call.OneCallWeatherDto
import com.usetech.x5weather.model.enums.WeatherType
import com.usetech.x5weather.model.model.*
import com.usetech.x5weather.network.ApiService
import com.usetech.x5weather.utils.loge
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class InfoWeatherRepository @Inject constructor(
    private val cityDao: CityDao,
    private val apiService: ApiService,
    private val currentWeatherDao: CurrentWeatherDao
) {
    fun getFullWeatherByCityId(id: Long): Single<FullWeatherModel> {
        return cityDao.getCityById(id)
            .flatMap { list ->
                list.lastOrNull()?.let { city ->
                    apiService.getFullWeatherByCoordinate(
                        city.latitude,
                        city.longitude
                    ).map {
                        it.body()
                    }
                }
            }
            .flatMap { oneCallWeatherDto ->
                return@flatMap Single.fromCallable {
                    return@fromCallable updateWeather(oneCallWeatherDto, id)
                }

            }
    }

    private fun updateWeather(
        oneCallWeatherDto: OneCallWeatherDto,
        cityId: Long
    ): FullWeatherModel {
        val fullWeatherModel = FullWeatherModel()

        /** обновляем в базу текущую погоду*/
        oneCallWeatherDto.currentWeather?.let { currentDto ->
            currentWeatherDao.deleteAllByTypeAndCityId(cityId, WeatherType.CURRENT)
            val model = currentDto.toModel(WeatherType.CURRENT, cityId, oneCallWeatherDto.timezone)
            currentWeatherDao.insertData(model.toEntity())

            fullWeatherModel.also {
                it.currentWeather = model
            }
        }

        /** обновляем почасовую */
        val hourlyListModel: MutableList<DailyWeatherModel> = mutableListOf()
        oneCallWeatherDto.hourlyWeather?.let { listDto ->
            currentWeatherDao.deleteAllByTypeAndCityId(cityId, WeatherType.HOURLY)
            listDto.forEach { baseDto ->
                val model = baseDto.toModel(WeatherType.HOURLY, cityId, oneCallWeatherDto.timezone)
                hourlyListModel.add(model)
                currentWeatherDao.insertData(model.toEntity())
            }
        }
        fullWeatherModel.also {
            it.hourlyWeather = hourlyListModel
        }
        /** обновляем по дням */
        val dailyListModel: MutableList<DailyWeatherModel> = mutableListOf()
        oneCallWeatherDto.dailyWeather?.let { listDto ->
            currentWeatherDao.deleteAllByTypeAndCityId(cityId, WeatherType.DAILY)
            listDto.forEach { baseDto ->
                val model = baseDto.toModel(WeatherType.DAILY, cityId, oneCallWeatherDto.timezone)
                dailyListModel.add(model)
                currentWeatherDao.insertData(model.toEntity())
            }
        }
        fullWeatherModel.also {
            it.dailyWeather = dailyListModel
        }

        return fullWeatherModel
    }

    fun removeFromCityList(cityId: Long): Single<Int> {
        return Single.fromCallable {
            try {
                currentWeatherDao.deleteAllByCityId(cityId)
                return@fromCallable true
            } catch (e: SQLiteException) {
                loge("Error delete weather ", e)
                throw e
            }
        }.flatMap {
            if (it) {
                return@flatMap Single.fromCallable {
                    try {
                        cityDao.removeFromCityList(cityId)
                        return@fromCallable true
                    } catch (e: SQLiteException) {
                        loge("Error delete weather ", e)
                        throw e
                    }
                }
            } else {
                return@flatMap Single.fromCallable {
                    loge("Error delete city weather")
                    return@fromCallable false
                }
            }
        }.flatMap {
            if (it){
                return@flatMap cityDao.getAllSelectedCity()
            } else {
                Single.fromCallable {
                    return@fromCallable 0
                }
            }
        }
    }

    fun getWeatherDatabaseByCityId(id: Long): Single<List<CurrentWeatherModel>> {
        return currentWeatherDao.getWeatherById(id).map { list ->
            list.map {
                it.toModel()
            }
        }
    }

    fun getCityListSelected(): Single<List<CityModel>> {
        return cityDao.getCityListSelected().flatMap { listDefault ->
            cityDao.getCityListSelectedNotDefault().map {
                listDefault.addAll(it)
                return@map listDefault.map { city ->
                    city.toModel()
                }
            }
        }
    }

    fun getCityWithWeather(): Single<List<CityWeatherModel>> {
        return currentWeatherDao.getCurrentWeatherWithDefaultCity().flatMap { listDefault ->
            currentWeatherDao.getCurrentWeatherWithCityNoDefault().map {
                listDefault.addAll(it)
                return@map listDefault.map { city ->
                    city.toModel()
                }
            }
        }
    }
}