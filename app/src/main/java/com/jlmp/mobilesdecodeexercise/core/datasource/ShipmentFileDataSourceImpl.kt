package com.jlmp.mobilesdecodeexercise.core.datasource

import com.jlmp.data.ShipmentFileDataSource
import com.jlmp.domain.Shipment
import com.jlmp.mobilesdecodeexercise.core.rawfile.RawShipmentParser
import com.jlmp.mobilesdecodeexercise.core.rawfile.toDomain
import javax.inject.Inject

class ShipmentFileDataSourceImpl @Inject constructor(
    private val rawShipmentParser: RawShipmentParser
): ShipmentFileDataSource {

    override suspend fun getShipments(): List<Shipment> {
        return rawShipmentParser.getRawShipment().toDomain()
    }
}