package com.jlmp.data

import com.jlmp.domain.Driver

interface DriverFileDataSource {
    suspend fun getDrivers(): List<Driver>
}