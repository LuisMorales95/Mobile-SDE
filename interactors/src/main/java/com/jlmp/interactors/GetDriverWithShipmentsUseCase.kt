package com.jlmp.interactors

import com.jlmp.domain.DriverWithShipment
import com.jlmp.domain.repository.DriverRepository
import javax.inject.Inject

class GetDriverWithShipmentsUseCase @Inject constructor(
    val driverRepository: DriverRepository
): BaseCase<Long, DriverWithShipment>() {
    override suspend fun invoke(data: Long): DriverWithShipment {
        return driverRepository.getDriver(data)
    }
}