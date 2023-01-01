package com.jlmp.mobilesdecodeexercise.core.datasource

import com.jlmp.data.DriverFileDataSource
import com.jlmp.domain.Driver
import com.jlmp.mobilesdecodeexercise.core.rawfile.RawDriverParser
import com.jlmp.mobilesdecodeexercise.core.rawfile.toDomain
import javax.inject.Inject

class DriverFileDataSourceImpl @Inject constructor(
    private val rawDriverParser: RawDriverParser
): DriverFileDataSource {
    override suspend fun getDrivers(): List<Driver> {
       return rawDriverParser.getRawDriver().toDomain()
    }
}