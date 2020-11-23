package com.usetech.x5weather.ui.city_list.city_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.usetech.x5weather.R
import com.usetech.x5weather.base.BaseFragment
import com.usetech.x5weather.di.city.CityModule
import com.usetech.x5weather.model.model.CityWeatherModel
import com.usetech.x5weather.utils.WeatherApplication
import kotlinx.android.synthetic.main.fragment_city_list.*

class CityListFragment : BaseFragment(),
    ChooseCityView {
    @InjectPresenter
    lateinit var presenter: ChooseCityPresenter

    @ProvidePresenter
    fun createPresenter(): ChooseCityPresenter {
        return WeatherApplication.sAppComponent.plus(CityModule()).getPresenter()
    }

    private var listener: OnFragmentActionListener? = null

    private var adapterCity =
        CityListWithWeatherAdapter(deleteCity = { cityId ->
            presenter.removeFromCityList(cityId)
        },
            clickByCity = {
                listener?.selectPositionCity(it)
            })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            val swipeToDeleteCallback = object : SwipeToDeleteCallback(adapterCity, it) {}
            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
            itemTouchHelper.attachToRecyclerView(rvCityList)
        }
        presenter.getCity()

        rvCityList.adapter = adapterCity

        btnAdd.setOnClickListener {
            listener?.goToAddCityFragment()
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
        fun goToAddCityFragment()
        fun selectPositionCity(position: Int)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CityListFragment()
    }

    override fun showError(fullScreenError: Boolean, message: String) {
        //nothing
    }

    //region Presenter
    override fun setCity(cityList: List<CityWeatherModel>) {
        adapterCity.setNewData(cityList)
    }

    override fun disableEnableBtnAdd(isActive: Boolean) {
        btnAdd.isEnabled = isActive
    }

    //endregion
}