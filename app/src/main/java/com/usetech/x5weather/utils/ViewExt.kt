package com.usetech.x5weather.utils

import android.view.View

fun View.show(needShow: Boolean = true) {
    visibility = if (needShow) View.VISIBLE else View.GONE
}

fun View.hide() {
    visibility = View.GONE
}
