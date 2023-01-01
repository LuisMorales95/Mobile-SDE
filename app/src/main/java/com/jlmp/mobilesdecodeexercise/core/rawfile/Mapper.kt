package com.jlmp.mobilesdecodeexercise.core.rawfile

import com.jlmp.domain.Driver
import com.jlmp.domain.Shipment


fun RawDriver.toDomain() : List<Driver> {
    return drivers.map {
        Driver(0, it,0)
    }
}

fun RawShipment.toDomain(): List<Shipment> {
    return shipments.map {
        Shipment(0, it)
    }
}