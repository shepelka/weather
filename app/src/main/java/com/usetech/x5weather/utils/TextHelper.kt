package com.usetech.x5weather.utils

object TextHelper {
    fun getWindTextFromDegree(degree: Int): String {
        var wind = WindEnum.NONE
        when (degree) {
            in 337..360 -> wind = WindEnum.N
            in 0..23 -> wind = WindEnum.N
            in 23..68 -> wind = WindEnum.NE
            in 68..113 -> wind = WindEnum.E
            in 113..158 -> wind = WindEnum.SE
            in 158..203 -> wind = WindEnum.S
            in 203..248 -> wind = WindEnum.SW
            in 248..293 -> wind = WindEnum.W
            in 293..337 -> wind = WindEnum.NW
        }
        return wind.windDegree
    }
}

enum class WindEnum(val windDegree: String) {
    N("С"),     //    337 - 23
    NE("СВ"),  //     23-68
    E("В"),     //      68-113
    SE("ЮВ"),    //     113-158
    S("Ю"),     // 158-203
    SW("ЮЗ"),   // 203-248
    W("З"),     // 248-293
    NW("СЗ"),   // 293-337
    NONE("")

    //N - Север
    //E - Восток
    //S - Юг
    //W - Запад
}