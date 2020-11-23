package com.usetech.x5weather.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.usetech.x5weather.R
import com.usetech.x5weather.di.main.MainModule
import com.usetech.x5weather.model.model.CityModel
import com.usetech.x5weather.ui.city_list.CityListActivity
import com.usetech.x5weather.ui.info_weather.InfoWeatherFragment
import com.usetech.x5weather.utils.WeatherApplication
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MainView,
    InfoWeatherFragment.OnFragmentActionListener {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return WeatherApplication.sAppComponent.plus(MainModule()).getMainPresenter()
    }

    private var adapterInfoSlider = InfoSliderAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        infoSlider.adapter = adapterInfoSlider
        infoSlider.requestDisallowInterceptTouchEvent(false)
        infoCountIndicator.setViewPager(infoSlider)

        presenter.getCityList()
    }

    override fun onRestart() {
        super.onRestart()
        presenter.getCityList()
    }

    companion object {
        const val POSITION_SELECTED = "position_selected"
        const val REQUEST_CODE = 101
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            val page = data?.getIntExtra(POSITION_SELECTED,0) ?: 0
            infoSlider.currentItem = page
            presenter.setPage(page)
        }
    }

    //region Presenter
    override fun setCityList(cityList: List<CityModel>) {
        adapterInfoSlider.setNewData(cityList)
        infoCountIndicator.setViewPager(infoSlider)
    }

    override fun setPage(page: Int) {
        infoSlider.currentItem = page
    }
    //endregion

    //region InfoWeatherFragment
    override fun goToCityList() {
        val intent = Intent(this, CityListActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }
    //endregion

}