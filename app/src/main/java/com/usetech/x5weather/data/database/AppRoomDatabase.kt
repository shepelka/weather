package com.usetech.x5weather.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.usetech.x5weather.data.database.dao.CityDao
import com.usetech.x5weather.data.database.dao.CurrentWeatherDao
import com.usetech.x5weather.data.database.table.CityEntity
import com.usetech.x5weather.data.database.table.CurrentWeatherEntity

@Database(
    entities = [
        CityEntity::class,
        CurrentWeatherEntity::class
    ], version = 1, exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase =
            INSTANCE
                ?: synchronized(this) {
                    INSTANCE
                        ?: buildDatabase(
                            context
                        )
                            .also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(
                context.applicationContext,
                AppRoomDatabase::class.java,
                "weather_database"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun cityDao(): CityDao
    abstract fun currentWeatherDao(): CurrentWeatherDao

}