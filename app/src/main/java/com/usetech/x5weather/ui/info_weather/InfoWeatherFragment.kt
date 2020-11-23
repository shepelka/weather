package com.usetech.x5weather.ui.info_weather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.usetech.x5weather.R
import com.usetech.x5weather.base.BaseFragment
import com.usetech.x5weather.di.info_weather.InfoWeatherModule
import com.usetech.x5weather.model.enums.WeatherStateEnum
import com.usetech.x5weather.model.model.CurrentWeatherModel
import com.usetech.x5weather.model.model.DailyWeatherModel
import com.usetech.x5weather.utils.TextHelper
import com.usetech.x5weather.utils.TimeHelper
import com.usetech.x5weather.utils.WeatherApplication
import kotlinx.android.synthetic.main.fragment_info_weather.*

class InfoWeatherFragment : BaseFragment(), InfoWeatherView {

    @InjectPresenter
    lateinit var presenter: InfoWeatherPresenter

    @ProvidePresenter
    fun createPresenter(): InfoWeatherPresenter {
        return WeatherApplication.sAppComponent.plus(InfoWeatherModule()).getPresenter()
    }

    private var listener: OnFragmentActionListener? = null
    private var idCity: Long = 0L
    private var nameCity: String = ""

    private var adapterHourly = HourlyAdapter()
    private var adapterDaily = DailyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idCity = it.getLong(ID_CITY_ARG, 0)
            nameCity = it.getString(NAME_CITY_ARG, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setCity(idCity, nameCity)
        presenter.getWeatherInfo()

        tvCity.text = nameCity

        rvHourly.adapter = adapterHourly
        rvDaily.adapter = adapterDaily

        ivMenu.setOnClickListener {
            listener?.goToCityList()
        }
    }


    companion object {
        private const val ID_CITY_ARG = "id_city_arg"
        private const val NAME_CITY_ARG = "name_city_arg"

        @JvmStatic
        fun newInstance(cityId: Long, nameCity: String) = InfoWeatherFragment().apply {
            arguments = Bundle().apply {
                putLong(ID_CITY_ARG, cityId)
                putString(NAME_CITY_ARG, nameCity)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener =
            if (parentFragment != null && parentFragment is OnFragmentActionListener) {
                parentFragment as OnFragmentActionListener
            } else if (context is OnFragmentActionListener) {
                context
            } else {
                throw RuntimeException("$context must implement OnFragmentActionListener")
            }
    }

    interface OnFragmentActionListener {
        fun goToCityList()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun showError(fullScreenError: Boolean, message: String) {
        //nothing
    }

    //region presenter
    override fun setWeatherInfo(weather: CurrentWeatherModel) {
        ivState.setImageResource(weather.state.iconRes)
        tvStateWeather.text = getString(weather.state.description) // состояние погоды сейчас
        if (weather.temp > 0) {
            tvTempCurrent.text =
                getString(R.string.string_temp_format_plus, weather.temp) // temp now
        } else {
            tvTempCurrent.text = getString(R.string.string_temp_format_minus, weather.temp)
        }
        val pressure = (weather.pressure * 0.75).toInt()
        tvPressure.text = getString(R.string.string_pressure_format, pressure) // давление
        tvHumidity.text = getString(R.string.string_humidity_format, weather.humidity) // влажность
        tvWind.text = getString(
            R.string.string_wind_format,
            TextHelper.getWindTextFromDegree(weather.windDegrees),
            weather.windSpeed
        )  // ветер

        tvDate.text = getString(
            R.string.string_today_date,
            TimeHelper.convertTimeToDateMonthFormat(weather.date, weather.timezone)
        )
//        //background state
        activity?.let {
            when (weather.state) {
                WeatherStateEnum.CLEAR_SKY ->
                    vBackgroundState.background = ContextCompat.getDrawable(it, R.drawable.blue)
                WeatherStateEnum.FEW_CLOUDS ->
                    vBackgroundState.background =
                        ContextCompat.getDrawable(it, R.drawable.lightgrey_blue)
                WeatherStateEnum.SCATTERED_CLOUDS ->
                    vBackgroundState.background =
                        ContextCompat.getDrawable(it, R.drawable.grey_blue)
                WeatherStateEnum.MIST ->
                    vBackgroundState.background =
                        ContextCompat.getDrawable(it, R.drawable.grey_mist)
                else -> vBackgroundState.background =
                    ContextCompat.getDrawable(it, R.drawable.darkgrey)
            }
        }
    }

    override fun showLoadError() {
        Snackbar.make(
            main, getString(R.string.content_error_not_get_data), Snackbar.LENGTH_LONG
        ).show()
    }

    override fun setWeatherHourly(weather: List<DailyWeatherModel>) {
        adapterHourly.setNewData(weather)
    }

    override fun setWeatherDaily(weather: List<DailyWeatherModel>) {
        adapterDaily.setNewData(weather)
    }

    //endregion
}