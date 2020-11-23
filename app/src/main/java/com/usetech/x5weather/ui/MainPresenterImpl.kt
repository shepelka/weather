package com.usetech.x5weather.ui

import com.arellomobile.mvp.InjectViewState
import com.usetech.x5weather.domain.CityUseCase
import com.usetech.x5weather.utils.performOnIO
import javax.inject.Inject

@InjectViewState
class MainPresenterImpl @Inject constructor(
    private val useCase: CityUseCase
) : MainPresenter() {

    private var pageAdapter: Int = 0
    private var isNeedPageUpdate: Boolean = false

    override fun getCityList() {
        useCase.getCitySelected()
            .performOnIO()
            .subscribe({
                viewState.setCityList(it)
                if (isNeedPageUpdate) {
                    viewState.setPage(pageAdapter)
                    pageAdapter = 0
                    isNeedPageUpdate = false
                }
            }, {
                it.printStackTrace()
            }).also {
                addToCompositeDisposable(it)
            }
    }

    override fun setPage(page: Int) {
        pageAdapter = page
        isNeedPageUpdate = true
    }
}