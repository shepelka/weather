package com.usetech.x5weather.di.splash

import com.usetech.x5weather.ui.splash.SplashPresenter
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class])
@SplashScope
interface SplashComponent {
    fun getPresenter(): SplashPresenter
}