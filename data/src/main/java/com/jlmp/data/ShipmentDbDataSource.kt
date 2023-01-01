    package com.jlmp.data

import com.jlmp.domain.Shipment

interface ShipmentDbDataSource {
    suspend fun getAllShipments(): List<Shipment>
    suspend fun addShipment(shipments: List<Shipment>)
    suspend fun isEmpty(): Boolean
}