package com.usetech.x5weather.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.usetech.x5weather.data.database.table.BaseEntity

abstract class AbstractDao<T : BaseEntity> {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun update(data: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insert(vararg data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insert(data: T): Long?

    @Delete
    abstract fun delete(data: T)

    @Delete
    abstract fun deleteAll(data: List<T>)

    open fun insertData(vararg data: T) {
        data.forEach {
            it.setCreatedDate()
        }
        insert(*data)
    }

    open fun insertOne(data: T): Long {
        data.setCreatedDate()
        return insert(data) ?: throw SQLiteException("Error insert new ${data.javaClass.name}")
    }

    open fun updateData(data: List<T>) {
        data.forEach {
            it.updatedTime = System.currentTimeMillis()
        }
        update(data)
    }

    open fun updateData(vararg data: T) {
        updateData(data.toList())
    }
}