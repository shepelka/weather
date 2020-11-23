package com.usetech.x5weather.ui.info_weather

import com.arellomobile.mvp.InjectViewState
import com.usetech.x5weather.data.converters.toFullList
import com.usetech.x5weather.domain.InfoWeatherUseCase
import com.usetech.x5weather.model.model.CurrentWeatherModel
import com.usetech.x5weather.model.model.FullWeatherModel
import com.usetech.x5weather.utils.loge
import com.usetech.x5weather.utils.performOnIO
import io.reactivex.disposables.Disposable

@InjectViewState
class InfoWeatherPresenterImpl(private val useCase: InfoWeatherUseCase) : InfoWeatherPresenter() {

    private var idCity: Long = 0L
    private var nameCity: String = ""
    private var disposable: Disposable? = null
    private var timeLastUpd = 0L

    companion object {
        const val CASH_PERIOD = 3600000
    }

    override fun setCity(id: Long, name: String) {
        idCity = id
        nameCity = name
    }

    override fun getWeatherInfo() {
        useCase.getWeatherDatabaseByCityId(idCity)
            .performOnIO()
            .subscribe({
                setAllWeatherView(it.toFullList())

                if (System.currentTimeMillis() - timeLastUpd > CASH_PERIOD) {
                    updateFromServer()
                }
            }, {
                it.printStackTrace()
            })
            .also {
                addToCompositeDisposable(it)
            }
    }

    override fun updateFromServer() {
        useCase.getFullWeatherFromServer(idCity)
            .performOnIO()
            .subscribe({
                timeLastUpd = System.currentTimeMillis()
                setAllWeatherView(it)

                loge("successful update weather")
            }, {
                viewState.showLoadError()
                it.printStackTrace()
            })
            .also {
                addToCompositeDisposable(it)
            }
    }

    private fun setAllWeatherView(weather: FullWeatherModel){
        weather.currentWeather?.let { current ->
            viewState.setWeatherInfo(current)
        }
        weather.hourlyWeather?.let { hourly ->
            viewState.setWeatherHourly(hourly)
        }
        weather.dailyWeather?.let { daily ->
            viewState.setWeatherDaily(daily)
        }
    }

    override fun unsubscribe() {
        super.unsubscribe()
        disposable?.dispose()
        disposable = null
    }


}