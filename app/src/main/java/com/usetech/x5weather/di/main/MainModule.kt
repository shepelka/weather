package com.usetech.x5weather.di.main

import com.usetech.x5weather.domain.CityUseCase
import com.usetech.x5weather.domain.CityUseCaseImpl
import com.usetech.x5weather.repository.InfoWeatherRepository
import com.usetech.x5weather.ui.MainPresenter
import com.usetech.x5weather.ui.MainPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @MainScope
    internal fun providePresenter(useCase: CityUseCase): MainPresenter {
        return MainPresenterImpl(useCase)
    }

    @Provides
    @MainScope
    fun provideUseCase(repository: InfoWeatherRepository): CityUseCase {
        return CityUseCaseImpl(repository)
    }

}

//    @Provides
//    @InfoWeatherScope
//    fun provideUseCase(repository: InfoWeatherRepository): InfoWeatherUseCase {
//        return InfoWeatherUseCaseImpl(repository)
//    }