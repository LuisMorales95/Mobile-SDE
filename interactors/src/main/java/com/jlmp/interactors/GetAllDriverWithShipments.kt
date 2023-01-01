package com.jlmp.interactors

import com.jlmp.domain.DriverWithShipment
import com.jlmp.domain.repository.DriverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDriverWithShipments @Inject constructor(
   private val driverRepository: DriverRepository
): BaseCase<Unit, Flow<List<DriverWithShipment>>>() {
    override suspend fun invoke(data: Unit): Flow<List<DriverWithShipment>> {
        return driverRepository.getDriversFlow()
    }
}