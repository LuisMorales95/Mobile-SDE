package com.jlmp.mobilesdecodeexercise.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "TB_SHIPMENT",
    indices = [
        Index(value = ["address"], unique = true)
    ]
)
data class ShipmentEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var address: String = ""
)
