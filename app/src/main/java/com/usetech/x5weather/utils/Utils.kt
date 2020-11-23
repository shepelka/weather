package com.usetech.x5weather.utils

import android.content.Context
import java.io.IOException

@Throws(IOException::class)
fun Context.readJsonAsset(fileName: String): String {
    var jsonString = ""
    try {
        jsonString = assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return jsonString
    }
    return jsonString

}
