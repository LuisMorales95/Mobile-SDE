package com.jlmp.mobilesdecodeexercise.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.jlmp.domain.Driver
import com.jlmp.mobilesdecodeexercise.base.BaseDao
import com.jlmp.mobilesdecodeexercise.core.database.embedded.EmbeddedDriverWithShipment
import com.jlmp.mobilesdecodeexercise.core.database.entity.DriverEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverDao: BaseDao<DriverEntity> {

    @Transaction
    @Query("SELECT * FROM TB_DRIVER")
    fun getAllFlow(): Flow<List<EmbeddedDriverWithShipment>>

    @Transaction
    @Query("SELECT * FROM TB_DRIVER WHERE id = :id")
    suspend fun get(id: Long): EmbeddedDriverWithShipment

    @Query("SELECT * FROM TB_DRIVER")
    fun getAll(): List<DriverEntity>

    @Query("SELECT COUNT(*) FROM TB_DRIVER ")
    suspend fun isEmpty(): Int
}