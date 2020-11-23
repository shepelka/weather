package com.usetech.x5weather.utils

import java.text.SimpleDateFormat
import java.util.*


object TimeHelper {
    private const val TIME_DATE_MONTH_FORMAT_WEEK = "d MMMM, EEEE"
    private const val TIME_DATE_MONTH_FORMAT = "d MMMM"
    private const val TIME_SMALL_FORMAT = "HH:mm"

    fun roundToNextWholeHour(dateSeconds: Long, timeZone: String): String {
        val c: Calendar = GregorianCalendar()
        val date = Date(dateSeconds * 1000)
        c.time = date
        c.add(Calendar.HOUR, 1)
        c[Calendar.MINUTE] = 0
        val sdf = SimpleDateFormat(TIME_SMALL_FORMAT, Locale("ru"))
        sdf.timeZone = TimeZone.getTimeZone(timeZone)

        return sdf.format(c.time).orEmpty()
    }

    fun convertTimeToDateMonthFormat(timeStamp: Long, timeZone: String): String {
        val date = Date(timeStamp * 1000)
        val sdf = SimpleDateFormat(TIME_DATE_MONTH_FORMAT, Locale("ru"))
        sdf.timeZone = TimeZone.getTimeZone(timeZone)
        return sdf.format(date).orEmpty()
    }

    fun convertTimeToDateMonthWeekFormat(timeStamp: Long, timeZone: String): String {
        val date = Date(timeStamp * 1000)
        val sdf = SimpleDateFormat(TIME_DATE_MONTH_FORMAT_WEEK, Locale("ru"))
        sdf.timeZone = TimeZone.getTimeZone(timeZone)
        return sdf.format(date).orEmpty()
    }
}
