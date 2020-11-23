package com.usetech.x5weather.repository

import android.content.Context
import android.database.sqlite.SQLiteException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.usetech.x5weather.data.converters.toEntity
import com.usetech.x5weather.data.database.dao.CityDao
import com.usetech.x5weather.data.database.table.CityEntity
import com.usetech.x5weather.model.dto.AllCountriesDto
import com.usetech.x5weather.utils.Const.Companion.FILE_CITY_JSON
import com.usetech.x5weather.utils.loge
import com.usetech.x5weather.utils.readJsonAsset
import io.reactivex.Single
import java.lang.reflect.Type
import javax.inject.Inject

class SplashRepository @Inject constructor(
    private val cityDao: CityDao,
    private val context: Context
) {

    fun getCityList(): Single<Long> {
        return cityDao.getAllCity()
    }

    fun setCityListToDatabase(): Single<Boolean> {
        return Single.fromCallable {
            try {
                insertDefaultCity()
                return@fromCallable true
            } catch (e: SQLiteException) {
                loge("Error insert default city", e)
                throw e
            }
        }.flatMap {
            Single.fromCallable {
                try {
                    insertListFromJsonFile()
                    return@fromCallable true
                } catch (e: SQLiteException) {
                    loge("Error insert city from json", e)
                    throw e
                }
            }
        }
    }

    private fun insertListFromJsonFile() {
        val stringJson = context.readJsonAsset(FILE_CITY_JSON)

        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<AllCountriesDto?>?>() {}.type

        val cityList: List<AllCountriesDto> = gson.fromJson(stringJson, listUserType)
        cityList.forEach {
            cityDao.insertData(it.toEntity())
            loge("city: ${it.nameCity}")
        }
    }

    private fun insertDefaultCity() {
        cityDao.insertData(
            CityEntity().also {
                it.nameCity = "Москва"
                it.country = "Россия"
                it.latitude = 55.75222f
                it.longitude = 37.61556f
                it.isSelected = true
                it.isDefault = true
            }
        )
        cityDao.insertData(
            CityEntity().also {
                it.nameCity = "Минск"
                it.country = "Беларусь"
                it.latitude = 53.900002f
                it.longitude = 27.56667f
                it.isSelected = true
                it.isDefault = true
            }
        )
    }
}