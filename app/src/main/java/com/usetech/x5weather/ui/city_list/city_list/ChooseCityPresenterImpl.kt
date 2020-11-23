package com.usetech.x5weather.ui.city_list.city_list

import com.arellomobile.mvp.InjectViewState
import com.usetech.x5weather.domain.CityUseCase
import com.usetech.x5weather.utils.performOnIO

@InjectViewState
class ChooseCityPresenterImpl(private val useCase: CityUseCase) : ChooseCityPresenter() {
    override fun getCity() {
        useCase.getCityWithWeather()
            .performOnIO()
            .subscribe({
                viewState.disableEnableBtnAdd(it.size < 6)
                viewState.setCity(it)
            }, {
                it.printStackTrace()
            }).also {
                addToCompositeDisposable(it)
            }
    }

    override fun removeFromCityList(cityId: Long) {
        useCase.removeFromCityList(cityId)
            .performOnIO()
            .subscribe({
                viewState.disableEnableBtnAdd(it < 6)
            }, {
                it.printStackTrace()
            }).also {
                addToCompositeDisposable(it)
            }
    }
}
