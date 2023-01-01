package com.jlmp.mobilesdecodeexercise.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "TB_DRIVER",
    indices = [
        Index(value = ["name"], unique = true)
    ]
)
data class DriverEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = "",
    var idShipment: Long = 0L
)
