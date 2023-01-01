package com.jlmp.data

import com.jlmp.domain.Driver
import com.jlmp.domain.DriverWithShipment
import kotlinx.coroutines.flow.Flow

interface DriverDbDataSource {
    suspend fun getDriversFlow(): Flow<List<DriverWithShipment>>
    suspend fun getAllDrivers(): List<Driver>
    suspend fun getDriver(id: Long): DriverWithShipment
    suspend fun addDrivers(drivers: List<Driver>)
    suspend fun updateDriver(driver: Driver)
    suspend fun isEmpty(): Boolean
}