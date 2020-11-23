package com.usetech.x5weather.utils

import android.util.Log

fun Any.loge(message: String, e: Throwable? = null) {
    Log.e(this::class.java.simpleName, message, e)
}

fun Any.logd(message: String) {
    Log.d(this::class.java.simpleName, message)
}