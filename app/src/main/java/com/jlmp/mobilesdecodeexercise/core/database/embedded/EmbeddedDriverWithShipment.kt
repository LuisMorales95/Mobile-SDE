package com.jlmp.mobilesdecodeexercise.core.database.embedded

import androidx.room.Embedded
import androidx.room.Relation
import com.jlmp.mobilesdecodeexercise.core.database.entity.DriverEntity
import com.jlmp.mobilesdecodeexercise.core.database.entity.ShipmentEntity

data class EmbeddedDriverWithShipment(
    @Embedded
    val driverEntity: DriverEntity,
    @Relation(entity = ShipmentEntity::class, parentColumn = "idShipment", entityColumn = "id")
    val shipmentEntity: ShipmentEntity?
)
