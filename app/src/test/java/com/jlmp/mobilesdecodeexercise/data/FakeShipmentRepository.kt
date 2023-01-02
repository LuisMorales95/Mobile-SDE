package com.jlmp.mobilesdecodeexercise.data

import com.jlmp.domain.Shipment
import com.jlmp.domain.repository.ShipmentRepository
import com.jlmp.mobilesdecodeexercise.shipmentList

class FakeShipmentRepository: ShipmentRepository {

    override suspend fun getAll(): List<Shipment> {
        return shipmentList
    }
}