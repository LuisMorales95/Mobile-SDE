package com.jlmp.mobilesdecodeexercise.core.datasource

import com.jlmp.data.ShipmentDbDataSource
import com.jlmp.domain.Shipment
import com.jlmp.mobilesdecodeexercise.core.database.AppDatabase
import com.jlmp.mobilesdecodeexercise.core.database.mapper.toDomain
import com.jlmp.mobilesdecodeexercise.core.database.mapper.toEntity
import javax.inject.Inject
import kotlin.contracts.Returns

class ShipmentDbDataSourceImpl @Inject constructor(
    val database: AppDatabase
) : ShipmentDbDataSource {

    override suspend fun getAllShipments(): List<Shipment> {
        return database.shipmentDao().getAll().map { it.toDomain() }
    }

    override suspend fun addShipment(shipments: List<Shipment>) {
        database.shipmentDao().insertIgnore(shipments.map { it.toEntity() })
    }

    override suspend fun isEmpty(): Boolean {
       return database.shipmentDao().isEmpty() == 0
    }
}