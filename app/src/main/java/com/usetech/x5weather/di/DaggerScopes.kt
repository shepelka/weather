package com.usetech.x5weather.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerApplication

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerPresenter

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerPreferences

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerView