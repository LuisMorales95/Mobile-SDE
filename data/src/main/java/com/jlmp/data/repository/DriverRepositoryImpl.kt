package com.jlmp.data.repository

import com.jlmp.data.DriverDbDataSource
import com.jlmp.data.DriverFileDataSource
import com.jlmp.domain.Driver
import com.jlmp.domain.DriverWithShipment
import com.jlmp.domain.repository.DriverRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DriverRepositoryImpl(
    private val driverDbDataSource: DriverDbDataSource,
    private val driverFileDataSource: DriverFileDataSource
) : DriverRepository {

    override suspend fun getDriversFlow(): Flow<List<DriverWithShipment>> {
        return driverDbDataSource.getDriversFlow()
    }

    override suspend fun getDrivers(): List<Driver> {
        return withContext(Dispatchers.IO) {
            val fileDrivers = driverFileDataSource.getDrivers()
            if (fileDrivers.isNotEmpty() && driverDbDataSource.isEmpty()) {
                driverDbDataSource.addDrivers(fileDrivers)
            }
            driverDbDataSource.getAllDrivers()
        }
    }

    override suspend fun getDriver(id: Long): DriverWithShipment {
        return driverDbDataSource.getDriver(id)
    }

    override suspend fun updateDriver(driver: Driver) {
        driverDbDataSource.updateDriver(driver)
    }
}