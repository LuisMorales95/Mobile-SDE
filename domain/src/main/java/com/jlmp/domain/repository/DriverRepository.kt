package com.jlmp.domain.repository

import com.jlmp.domain.Driver
import com.jlmp.domain.DriverWithShipment
import kotlinx.coroutines.flow.Flow


interface DriverRepository {
    suspend fun getDriversFlow(): Flow<List<DriverWithShipment>>
    suspend fun getDrivers(): List<Driver>
    suspend fun getDriver(id: Long): DriverWithShipment
    suspend fun updateDriver(driver: Driver)
}