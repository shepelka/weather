package com.usetech.x5weather.ui.city_list.add_city

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.usetech.x5weather.R
import com.usetech.x5weather.base.BaseFragment
import com.usetech.x5weather.di.weather.WeatherModule
import com.usetech.x5weather.model.model.CityModel
import com.usetech.x5weather.utils.WeatherApplication
import com.usetech.x5weather.utils.show
import com.google.android.material.snackbar.Snackbar
import com.usetech.x5weather.utils.hide
import kotlinx.android.synthetic.main.fragment_add_city.*


class AddCityFragment : BaseFragment(),
    WeatherView {

    @InjectPresenter
    lateinit var presenter: AddCityPresenter

    @ProvidePresenter
    fun createPresenter(): AddCityPresenter {
        return WeatherApplication.sAppComponent.plus(WeatherModule()).getPresenter()
    }

    private var listener: OnFragmentActionListener? = null

    private var adapterFindCity = FindCityAdapter(
        selectedCity = {
            presenter.selectCity(it)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCityList.adapter = adapterFindCity

        ivClear.setOnClickListener {
            searchEditText.text.clear()
        }

        presenter.findCity()

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.findCity(s.toString())
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddCityFragment()
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

    interface OnFragmentActionListener

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun showError(fullScreenError: Boolean, message: String) {
        //nothing
    }

    //region presenter

    override fun setErrorSelected() {
        Snackbar.make(
            ccMain, getString(R.string.content_search_not_add), Snackbar.LENGTH_LONG
        ).show()
    }

    override fun successSelectedGoToList() {
        activity?.onBackPressed()
    }

    override fun setListCity(city: List<CityModel>) {
        adapterFindCity.setNewData(city)
        llNotFound.hide()
        relativeLayout.show()
    }

    override fun showNotFoundSearchResult(searchString: String) {
        adapterFindCity.setNewData(listOf())
        llNotFound.show()
        relativeLayout.hide()
    }

    override fun showProgress(isLoading: Boolean) {
        progress?.show(isLoading)
    }

    //endregion
}