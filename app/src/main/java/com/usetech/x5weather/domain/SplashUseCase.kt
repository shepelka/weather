package com.usetech.x5weather.domain

import com.usetech.x5weather.repository.SplashRepository
import io.reactivex.Single

interface SplashUseCase {
    fun getCityList(): Single<Boolean>
}

class SplashUseCaseImpl(
    private val repository: SplashRepository
) : SplashUseCase {
    override fun getCityList(): Single<Boolean> {
        return repository.getCityList().flatMap {
            if (it > 0) {
                return@flatMap Single.fromCallable {
                    return@fromCallable true
                }
            } else {
                return@flatMap repository.setCityListToDatabase()
            }
        }
    }
}