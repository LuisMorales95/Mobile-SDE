package com.jlmp.mobilesdecodeexercise.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIgnore(items: List<T>)

    @Delete
    suspend fun delete(item: T)

    @Update
    suspend fun update(item: T)
}