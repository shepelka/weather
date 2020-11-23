package com.usetech.x5weather.repository

import android.database.sqlite.SQLiteException
import com.usetech.x5weather.data.converters.toEntity
import com.usetech.x5weather.data.converters.toModel
import com.usetech.x5weather.data.database.dao.CityDao
import com.usetech.x5weather.data.database.dao.CurrentWeatherDao
import com.usetech.x5weather.model.enums.WeatherType
import com.usetech.x5weather.model.model.CityModel
import com.usetech.x5weather.model.model.DailyWeatherModel
import com.usetech.x5weather.network.ApiService
import com.usetech.x5weather.utils.loge
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class AddCityRepository @Inject constructor(
    private val cityDao: CityDao,
    private val apiService: ApiService,
    private val currentWeatherDao: CurrentWeatherDao
) {

    fun searchCityListByName(name: String): Flowable<List<CityModel>> {
        return cityDao.searchCityListByName(name).map { list ->
            list.map {
                it.toModel()
            }
        }
    }

    fun selectCityById(idCity: Long): Single<Boolean> {
        return Single.fromCallable {
            try {
                cityDao.selectCityById(idCity)
                return@fromCallable true
            } catch (e: SQLiteException) {
                loge("Error select city", e)
                throw e
            }
        }
    }

    fun getWeatherFromServer(city: CityModel): Single<Boolean> {
        return apiService.getFullWeatherByCoordinate(city.latitude, city.longitude)
            .map { response ->
                return@map if (response.isSuccessful) {
                    response.body()?.let { oneCallWeatherDto ->
                        /** обновляем в базу текущую погоду*/
                        oneCallWeatherDto.currentWeather?.let { currentDto ->
                            currentWeatherDao.deleteAllByTypeAndCityId(city.id, WeatherType.CURRENT)
                            val model = currentDto.toModel(
                                WeatherType.CURRENT,
                                city.id,
                                oneCallWeatherDto.timezone
                            )
                            currentWeatherDao.insertData(model.toEntity())
                        }

                        /** обновляем почасовую */
                        val hourlyListModel: MutableList<DailyWeatherModel> = mutableListOf()
                        oneCallWeatherDto.hourlyWeather?.let { listDto ->
                            currentWeatherDao.deleteAllByTypeAndCityId(city.id, WeatherType.HOURLY)
                            listDto.forEach { baseDto ->
                                val model = baseDto.toModel(
                                    WeatherType.HOURLY,
                                    city.id,
                                    oneCallWeatherDto.timezone
                                )
                                hourlyListModel.add(model)
                                currentWeatherDao.insertData(model.toEntity())
                            }
                        }
                        /** обновляем по дням */
                        val dailyListModel: MutableList<DailyWeatherModel> = mutableListOf()
                        oneCallWeatherDto.dailyWeather?.let { listDto ->
                            currentWeatherDao.deleteAllByTypeAndCityId(city.id, WeatherType.DAILY)
                            listDto.forEach { baseDto ->
                                val model = baseDto.toModel(
                                    WeatherType.DAILY,
                                    city.id,
                                    oneCallWeatherDto.timezone
                                )
                                dailyListModel.add(model)
                                currentWeatherDao.insertData(model.toEntity())
                            }
                        }
                    }
                    true
                } else {
                    false
                }
            }
    }

}