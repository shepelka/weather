package com.usetech.x5weather.ui.splash

import com.arellomobile.mvp.InjectViewState
import com.usetech.x5weather.domain.SplashUseCase
import com.usetech.x5weather.utils.performOnIO
import javax.inject.Inject

@InjectViewState
class SplashPresenterImpl @Inject constructor(
    private val useCase: SplashUseCase
) : SplashPresenter() {

    override fun getCityList() {
        useCase.getCityList()
            .performOnIO()
            .subscribe({
                viewState.goToMainActivity()
            }, {
                it.printStackTrace()
            }).also {
                addToCompositeDisposable(it)
            }
    }
}
