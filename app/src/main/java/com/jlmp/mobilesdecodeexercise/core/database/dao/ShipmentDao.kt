package com.jlmp.mobilesdecodeexercise.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.jlmp.domain.Shipment
import com.jlmp.mobilesdecodeexercise.base.BaseDao
import com.jlmp.mobilesdecodeexercise.core.database.entity.ShipmentEntity

@Dao
interface ShipmentDao: BaseDao<ShipmentEntity> {

    @Query("SELECT * FROM TB_SHIPMENT WHERE id = :id")
    suspend fun get(id: Long): ShipmentEntity

    @Query("SELECT * FROM TB_SHIPMENT")
    suspend fun getAll(): List<ShipmentEntity>

    @Query("SELECT COUNT(*) FROM TB_SHIPMENT")
    suspend fun isEmpty(): Int
}