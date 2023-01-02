package com.jlmp.interactors

import com.jlmp.domain.highestScore
import com.jlmp.domain.repository.DriverRepository
import com.jlmp.domain.repository.ShipmentRepository
import javax.inject.Inject

class AssignShipmentsUseCase @Inject constructor(
    private val driverRepository: DriverRepository,
    private val shipmentRepository: ShipmentRepository
): BaseCase<Unit, Unit>() {

    override suspend fun invoke(data: Unit) {
        val shipment = shipmentRepository.getAll()
        val drivers = driverRepository.getDrivers().toMutableList()
        val sortedShipments = shipment.sortedByDescending { it.address.length }.toMutableList()
        while (sortedShipments.isNotEmpty()) {
            val s = sortedShipments.first()
            val isEven = s.address.length % 2 == 0

            val addressLength = s.address.length

            val driver = drivers.highestScore(isEven,addressLength)
            driver.idShipment = s.id
            driverRepository.updateDriver(driver)

            drivers.remove(driver)
            sortedShipments.remove(s)
            if (drivers.isEmpty() || sortedShipments.isEmpty()) break
        }
    }
}