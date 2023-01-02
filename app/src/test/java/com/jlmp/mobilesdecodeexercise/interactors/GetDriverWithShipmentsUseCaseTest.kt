package com.jlmp.mobilesdecodeexercise.interactors

import com.jlmp.domain.repository.DriverRepository
import com.jlmp.domain.repository.ShipmentRepository
import com.jlmp.interactors.AssignShipmentsUseCase
import com.jlmp.interactors.GetDriverWithShipmentsUseCase
import com.jlmp.mobilesdecodeexercise.MainCoroutineRule
import com.jlmp.mobilesdecodeexercise.data.FakeDriverRepository
import com.jlmp.mobilesdecodeexercise.data.FakeShipmentRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetDriverWithShipmentsUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var driverRepository: DriverRepository
    private lateinit var shipmentRepository: ShipmentRepository
    private lateinit var getDriverWithShipmentsUseCase: GetDriverWithShipmentsUseCase
    private lateinit var assignShipmentsUseCase: AssignShipmentsUseCase

    @Before
    fun setup() {
        driverRepository = FakeDriverRepository()
        shipmentRepository = FakeShipmentRepository()
        assignShipmentsUseCase = AssignShipmentsUseCase(driverRepository, shipmentRepository)
        getDriverWithShipmentsUseCase = GetDriverWithShipmentsUseCase(driverRepository)
    }

    @Test
    fun getDriverWithShipment() {
        mainCoroutineRule.runBlockingTest {
            assignShipmentsUseCase.invoke(Unit)
            val driverWithShipment = getDriverWithShipmentsUseCase.invoke(1L)
            assertThat(1L, IsEqual(driverWithShipment.driver.id))
            assertThat(5L, IsEqual(driverWithShipment.shipment?.id))
        }
    }
}