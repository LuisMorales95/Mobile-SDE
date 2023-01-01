package com.jlmp.data.repository

import com.jlmp.data.ShipmentDbDataSource
import com.jlmp.data.ShipmentFileDataSource
import com.jlmp.domain.Shipment
import com.jlmp.domain.repository.ShipmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShipmentRepositoryImpl @Inject constructor(
    private val shipmentDbDataSource: ShipmentDbDataSource,
    private val shipmentFileDataSource: ShipmentFileDataSource
): ShipmentRepository  {

    override suspend fun getAll(): List<Shipment> {
        return withContext(Dispatchers.IO){
            val fileShipments = shipmentFileDataSource.getShipments()
            if (fileShipments.isNotEmpty() && shipmentDbDataSource.isEmpty()) {
                shipmentDbDataSource.addShipment(fileShipments)
            }
            shipmentDbDataSource.getAllShipments()
        }
    }
}