package com.usetech.x5weather.di

import android.app.Application
import android.content.Context
import com.usetech.x5weather.data.database.AppRoomDatabase
import com.usetech.x5weather.data.database.dao.CityDao
import com.usetech.x5weather.data.database.dao.CurrentWeatherDao
import com.usetech.x5weather.network.ApiService
import com.usetech.x5weather.utils.WeatherApplication
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val app: WeatherApplication) {

    @PerApplication
    @Provides
    fun provideContext(): Context = app

    @PerApplication
    @Provides
    fun provideAppContext(): Application = app

    @PerApplication
    @Provides
    fun provideRoomDatabase(): AppRoomDatabase {
        return AppRoomDatabase.getInstance(app)
    }

    @PerApplication
    @Provides
    fun provideCityDao(appRoomDatabase: AppRoomDatabase): CityDao {
        return appRoomDatabase.cityDao()
    }

    @PerApplication
    @Provides
    fun provideCurrentWeatherDao(appRoomDatabase: AppRoomDatabase): CurrentWeatherDao {
        return appRoomDatabase.currentWeatherDao()
    }

    @PerApplication
    @Provides
    fun provideApiService(): ApiService {
        return ApiService.create()
    }
}