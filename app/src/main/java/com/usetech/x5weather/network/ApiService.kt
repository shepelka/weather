package com.usetech.x5weather.network

import com.usetech.x5weather.model.dto.one_call.OneCallWeatherDto
import com.usetech.x5weather.utils.Const.Companion.HOST_BASE_URL
import com.usetech.x5weather.utils.Const.Companion.KEY_API
import com.usetech.x5weather.utils.Const.Companion.LANGUAGE_API
import com.usetech.x5weather.utils.Const.Companion.METRIC_API
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //https://api.openweathermap.org/data/2.5/onecall?lat=15.99714&lon=-61.73214&exclude={part}&appid=f9812ddbba7642c939448012959d3302
    @GET("onecall")
    fun getFullWeatherByCoordinate(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("units") metric: String = METRIC_API,
        @Query("lang") lang: String = LANGUAGE_API,
        @Query("appid") key: String = KEY_API
    ): Single<Response<OneCallWeatherDto>>

    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HOST_BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java);
        }
    }
}