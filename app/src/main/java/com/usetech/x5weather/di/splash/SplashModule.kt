package com.usetech.x5weather.di.splash

import com.usetech.x5weather.domain.SplashUseCase
import com.usetech.x5weather.domain.SplashUseCaseImpl
import com.usetech.x5weather.repository.SplashRepository
import com.usetech.x5weather.ui.splash.SplashPresenter
import com.usetech.x5weather.ui.splash.SplashPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class SplashModule {
    @Provides
    @SplashScope
    internal fun providePresenter(useCase: SplashUseCase): SplashPresenter {
        return SplashPresenterImpl(
            useCase
        )
    }

    @Provides
    @SplashScope
    fun provideUseCase(repository: SplashRepository): SplashUseCase {
        return SplashUseCaseImpl(repository)
    }

}