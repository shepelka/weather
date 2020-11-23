package com.usetech.x5weather.di.weather

import com.usetech.x5weather.domain.WeatherUseCase
import com.usetech.x5weather.domain.WeatherUseCaseImpl
import com.usetech.x5weather.repository.AddCityRepository
import com.usetech.x5weather.ui.city_list.add_city.AddCityPresenter
import com.usetech.x5weather.ui.city_list.add_city.AddCityPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {
    @Provides
    @WeatherScope
    internal fun providePresenter(useCase: WeatherUseCase): AddCityPresenter {
        return AddCityPresenterImpl(
            useCase
        )
    }

    @Provides
    @WeatherScope
    fun provideUseCase(repository: AddCityRepository): WeatherUseCase {
        return WeatherUseCaseImpl(repository)
    }
}