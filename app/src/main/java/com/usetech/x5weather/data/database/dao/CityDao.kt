package com.usetech.x5weather.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.usetech.x5weather.data.database.table.CityEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class CityDao : AbstractDao<CityEntity>() {

    @Query("SELECT * FROM city WHERE is_selected = 1 AND is_default = 1 ORDER BY name DESC")
    abstract fun getCityListSelected(): Single<MutableList<CityEntity>>

    @Query("SELECT * FROM city WHERE is_selected = 1 AND is_default = 0 ORDER BY updated_time ASC")
    abstract fun getCityListSelectedNotDefault(): Single<List<CityEntity>>

    @Query("SELECT COUNT(*) FROM city")
    abstract fun getAllCity(): Single<Long>

    @Query("SELECT COUNT(*) FROM city WHERE is_selected = 1")
    abstract fun getAllSelectedCity(): Single<Int>

    @Query("SELECT * FROM city WHERE  id = :id")
    abstract fun getCityById(id: Long): Single<List<CityEntity>>

    @Query("SELECT * FROM city WHERE  id = :id")
    abstract fun getCityByIdSync(id: Long): CityEntity?

    @Query("SELECT * FROM city WHERE name LIKE  :name||'%' AND is_selected = 0 ORDER BY name ASC")
    abstract fun searchCityListByName(name: String): Flowable<List<CityEntity>>

    @Transaction
    open fun selectCityById(cityId: Long) {
        val city = getCityByIdSync(cityId)
        city?.let {
            it.apply {
                this.isSelected = true
            }
            updateData(it)
        }
    }

    @Transaction
    open fun removeFromCityList(cityId: Long) {
        val city = getCityByIdSync(cityId)
        city?.let {
            it.apply {
                this.isSelected = false
            }
            updateData(it)
        }
    }
}