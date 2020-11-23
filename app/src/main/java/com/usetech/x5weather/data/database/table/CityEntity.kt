package com.usetech.x5weather.data.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "city")
open class CityEntity : BaseEntity() {
    @ColumnInfo(name = "name")
    var nameCity: String = ""

    @ColumnInfo(name = "is_selected")
    var isSelected: Boolean = false

    /** @isDefault - запрещено удаление городов со значением true*/
    @ColumnInfo(name = "is_default")
    var isDefault: Boolean = false

    @ColumnInfo(name = "latitude")
    var latitude: Float = 0f

    @ColumnInfo(name = "longitude")
    var longitude: Float = 0f

    @ColumnInfo(name = "country")
    var country: String = ""
}