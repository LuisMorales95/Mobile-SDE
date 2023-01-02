package com.jlmp.mobilesdecodeexercise.core.datasource

import com.jlmp.data.DriverDbDataSource
import com.jlmp.domain.Driver
import com.jlmp.domain.DriverWithShipment
import com.jlmp.mobilesdecodeexercise.core.database.AppDatabase
import com.jlmp.mobilesdecodeexercise.core.database.mapper.toDomain
import com.jlmp.mobilesdecodeexercise.core.database.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DriverDbDataSourceImpl @Inject constructor(
    val database: AppDatabase
) : DriverDbDataSource {

    override suspend fun getDriversFlow(): Flow<List<DriverWithShipment>> {
        return database.driverDao().getAllDriverWithShipmentFlow().map {
            it.map { it.toDomain() }
        }
    }

    override suspend fun getAllDrivers(): List<Driver> {
        return database.driverDao().getAll().map { it.toDomain() }
    }

    override suspend fun getDriver(id: Long): DriverWithShipment {
        return database.driverDao().getDriverWithShipmentById(id).toDomain()
    }

    override suspend fun addDrivers(drivers: List<Driver>) {
        database.driverDao().insertIgnore(drivers.map { it.toEntity() })
    }

    override suspend fun isEmpty(): Boolean {
        return database.driverDao().isEmpty() == 0
    }

    override suspend fun updateDriver(driver: Driver) {
        database.driverDao().update(driver.toEntity())
    }
}