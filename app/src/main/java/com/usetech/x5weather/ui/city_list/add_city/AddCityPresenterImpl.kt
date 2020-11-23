package com.usetech.x5weather.ui.city_list.add_city

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.usetech.x5weather.domain.WeatherUseCase
import com.usetech.x5weather.model.model.CityModel
import com.usetech.x5weather.utils.performOnIO
import io.reactivex.disposables.Disposable

@InjectViewState
class AddCityPresenterImpl(private val useCase: WeatherUseCase) : AddCityPresenter() {
    companion object {
        private const val DELAY_FOR_REQUEST_MS = 300L
    }

    private val handler = Handler()
    private var listenerDataBase: Disposable? = null
    private var search = ""

    private val searchTask = Runnable {
        useCase
            .findListCity(search)
            .performOnIO()
            .subscribe({
                if (it.isEmpty()) {
                    viewState.showNotFoundSearchResult(search)
                } else {
                    viewState.setListCity(it)
                }
            }, {
                viewState.showNotFoundSearchResult(search)
                it.printStackTrace()
            })
            .also {
                listenerDataBase = it
                addToCompositeDisposable(it)
            }
    }

    override fun findCity(partName: String) {
        handler.removeCallbacks(searchTask)
        listenerDataBase?.dispose()
        listenerDataBase = null
        search = partName
        handler.postDelayed(
            searchTask,
            DELAY_FOR_REQUEST_MS
        )
    }

    override fun selectCity(city: CityModel) {
        useCase.selectCity(city)
            .performOnIO()
            .doOnSubscribe {
                viewState.showProgress(true)
            }
            .doFinally {
                viewState.showProgress(false)
            }
            .subscribe({
                if (it) {
                    viewState.successSelectedGoToList()
                } else {
                    viewState.setErrorSelected()
                }
            }, {
                it.printStackTrace()
            }).also {
                addToCompositeDisposable(it)
            }
    }
}
