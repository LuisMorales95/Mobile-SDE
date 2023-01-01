package com.jlmp.data

import com.jlmp.domain.Shipment

interface ShipmentFileDataSource {
    suspend fun getShipments(): List<Shipment>
}