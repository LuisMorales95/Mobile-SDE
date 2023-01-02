package com.jlmp.mobilesdecodeexercise.data

import com.jlmp.domain.Driver
import com.jlmp.domain.DriverWithShipment
import com.jlmp.domain.repository.DriverRepository
import com.jlmp.mobilesdecodeexercise.driverList
import com.jlmp.mobilesdecodeexercise.shipmentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDriverRepository: DriverRepository {



    override suspend fun getDriversFlow(): Flow<List<DriverWithShipment>> {
        return flow {
            emit(driverList.map { d ->
                DriverWithShipment(
                    driver = d,
                    shipment = shipmentList.find { s -> d.id == s.id }
                )
            })
        }
    }

    override suspend fun getDrivers(): List<Driver> {
        return driverList
    }

    override suspend fun getDriver(id: Long): DriverWithShipment {
        val driver = driverList.find { it.id == id } ?: Driver(0L, "John Doe")
        return DriverWithShipment(
            driver = driver,
            shipment = shipmentList.find { it.id == driver.idShipment }
        )
    }

    override suspend fun updateDriver(driver: Driver) {
        driverList.find { d -> d.id == driver.id }?.idShipment = driver.idShipment
    }
}