package com.usetech.x5weather.di.main

import com.usetech.x5weather.ui.MainPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@MainScope
interface MainComponent {
    fun getMainPresenter(): MainPresenter
}