package com.jlmp.mobilesdecodeexercise.core.di.modules

import com.jlmp.data.DriverDbDataSource
import com.jlmp.data.DriverFileDataSource
import com.jlmp.data.ShipmentDbDataSource
import com.jlmp.data.ShipmentFileDataSource
import com.jlmp.mobilesdecodeexercise.core.datasource.DriverDbDataSourceImpl
import com.jlmp.mobilesdecodeexercise.core.datasource.DriverFileDataSourceImpl
import com.jlmp.mobilesdecodeexercise.core.datasource.ShipmentDbDataSourceImpl
import com.jlmp.mobilesdecodeexercise.core.datasource.ShipmentFileDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsDriverDbDataSource(driverDbDataSourceImpl: DriverDbDataSourceImpl): DriverDbDataSource

    @Binds
    abstract fun bindsShipmentDbDataSource(shipmentDbDataSourceImpl: ShipmentDbDataSourceImpl): ShipmentDbDataSource

    @Binds
    abstract fun bindsDriverFileDataSource(driverFileDataSourceImpl: DriverFileDataSourceImpl): DriverFileDataSource

    @Binds
    abstract fun bindsShipmentFileDataSource(driverShipmentFileDataSourceImpl: ShipmentFileDataSourceImpl): ShipmentFileDataSource

}