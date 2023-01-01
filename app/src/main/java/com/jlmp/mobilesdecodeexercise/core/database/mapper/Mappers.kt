package com.jlmp.mobilesdecodeexercise.core.database.mapper

import com.jlmp.domain.Driver
import com.jlmp.domain.DriverWithShipment
import com.jlmp.domain.Shipment
import com.jlmp.mobilesdecodeexercise.core.database.embedded.EmbeddedDriverWithShipment
import com.jlmp.mobilesdecodeexercise.core.database.entity.DriverEntity
import com.jlmp.mobilesdecodeexercise.core.database.entity.ShipmentEntity

fun DriverEntity.toDomain() = Driver(id, name, idShipment)

fun Driver.toEntity() = DriverEntity(id, name, idShipment)

fun ShipmentEntity.toDomain() = Shipment(id, address)

fun Shipment.toEntity() = ShipmentEntity(id, address)

fun EmbeddedDriverWithShipment.toDomain() = DriverWithShipment(driverEntity.toDomain(), shipmentEntity?.toDomain())

fun DriverWithShipment.toEntity() = EmbeddedDriverWithShipment(driver.toEntity(), shipment?.toEntity())