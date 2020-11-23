package com.usetech.x5weather.di.info_weather

import com.usetech.x5weather.domain.InfoWeatherUseCase
import com.usetech.x5weather.domain.InfoWeatherUseCaseImpl
import com.usetech.x5weather.repository.InfoWeatherRepository
import com.usetech.x5weather.ui.info_weather.InfoWeatherPresenter
import com.usetech.x5weather.ui.info_weather.InfoWeatherPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class InfoWeatherModule {
    @Provides
    @InfoWeatherScope
    internal fun providePresenter(useCase: InfoWeatherUseCase): InfoWeatherPresenter {
        return InfoWeatherPresenterImpl(useCase)
    }

    @Provides
    @InfoWeatherScope
    fun provideUseCase(repository: InfoWeatherRepository): InfoWeatherUseCase {
        return InfoWeatherUseCaseImpl(repository)
    }
}