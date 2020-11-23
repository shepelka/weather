package com.usetech.x5weather.ui.city_list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.usetech.x5weather.R
import com.usetech.x5weather.ui.MainActivity.Companion.POSITION_SELECTED
import com.usetech.x5weather.ui.city_list.add_city.AddCityFragment
import com.usetech.x5weather.ui.city_list.city_list.CityListFragment

class CityListActivity : MvpAppCompatActivity(),
    AddCityFragment.OnFragmentActionListener,
    CityListFragment.OnFragmentActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.globalFragmentContainer, CityListFragment.newInstance())
            .commit()
    }


    //region CityListFragment
    override fun goToAddCityFragment() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.globalFragmentContainer, AddCityFragment.newInstance())
            .commit()
    }

    override fun selectPositionCity(position: Int) {
        val intent = Intent(this, CityListActivity::class.java)
        intent.putExtra(POSITION_SELECTED, position)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    //endregion
}
