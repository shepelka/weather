package com.usetech.x5weather.di.city

import com.usetech.x5weather.domain.CityUseCase
import com.usetech.x5weather.domain.CityUseCaseImpl
import com.usetech.x5weather.repository.InfoWeatherRepository
import com.usetech.x5weather.ui.city_list.city_list.ChooseCityPresenter
import com.usetech.x5weather.ui.city_list.city_list.ChooseCityPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class CityModule {
    @Provides
    @CityScope
    internal fun providePresenter(useCase: CityUseCase): ChooseCityPresenter {
        return ChooseCityPresenterImpl(
            useCase
        )
    }

    @Provides
    @CityScope
    fun provideUseCase(repository: InfoWeatherRepository): CityUseCase {
        return CityUseCaseImpl(repository)
    }
}