package com.jlmp.mobilesdecodeexercise.core.di.modules

import com.jlmp.data.DriverDbDataSource
import com.jlmp.data.DriverFileDataSource
import com.jlmp.data.ShipmentDbDataSource
import com.jlmp.data.ShipmentFileDataSource
import com.jlmp.data.repository.DriverRepositoryImpl
import com.jlmp.data.repository.ShipmentRepositoryImpl
import com.jlmp.domain.repository.DriverRepository
import com.jlmp.domain.repository.ShipmentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesDriverRepository(driverDbDataSource: DriverDbDataSource, driverFileDataSource: DriverFileDataSource): DriverRepository {
        return DriverRepositoryImpl(driverDbDataSource,driverFileDataSource)
    }

    @Singleton
    @Provides
    fun providesShipmentRepository(shipmentDbDataSource: ShipmentDbDataSource, shipmentFileDataSource: ShipmentFileDataSource): ShipmentRepository {
        return ShipmentRepositoryImpl(shipmentDbDataSource,shipmentFileDataSource)
    }
}