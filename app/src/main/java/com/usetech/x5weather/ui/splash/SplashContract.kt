package com.usetech.x5weather.ui.splash

import com.usetech.x5weather.base.BasePresenter
import com.usetech.x5weather.base.BaseView

interface SplashView : BaseView {
    fun goToMainActivity()
}

abstract class SplashPresenter : BasePresenter<SplashView>() {
    abstract fun getCityList()
}