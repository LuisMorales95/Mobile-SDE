package com.jlmp.mobilesdecodeexercise.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jlmp.mobilesdecodeexercise.core.database.dao.DriverDao
import com.jlmp.mobilesdecodeexercise.core.database.dao.ShipmentDao
import com.jlmp.mobilesdecodeexercise.core.database.entity.DriverEntity
import com.jlmp.mobilesdecodeexercise.core.database.entity.ShipmentEntity

@Database(
    entities = [
        DriverEntity::class,
        ShipmentEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun driverDao(): DriverDao
    abstract fun shipmentDao(): ShipmentDao
}