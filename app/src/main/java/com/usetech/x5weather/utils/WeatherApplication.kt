package com.usetech.x5weather.utils

import androidx.multidex.MultiDexApplication
import com.usetech.x5weather.di.AppComponent
import com.usetech.x5weather.di.AppModule
import com.usetech.x5weather.di.DaggerAppComponent

class WeatherApplication : MultiDexApplication() {
    companion object {
        lateinit var sAppComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        sAppComponent = createAppComponent()

    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}