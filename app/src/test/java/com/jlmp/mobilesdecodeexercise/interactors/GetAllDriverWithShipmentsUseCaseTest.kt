package com.jlmp.mobilesdecodeexercise.interactors

import com.jlmp.domain.DriverWithShipment
import com.jlmp.domain.repository.DriverRepository
import com.jlmp.domain.repository.ShipmentRepository
import com.jlmp.interactors.AssignShipmentsUseCase
import com.jlmp.interactors.GetAllDriverWithShipmentsUseCase
import com.jlmp.interactors.GetDriverWithShipmentsUseCase
import com.jlmp.mobilesdecodeexercise.MainCoroutineRule
import com.jlmp.mobilesdecodeexercise.core.database.embedded.EmbeddedDriverWithShipment
import com.jlmp.mobilesdecodeexercise.data.FakeDriverRepository
import com.jlmp.mobilesdecodeexercise.data.FakeShipmentRepository
import com.jlmp.mobilesdecodeexercise.driverList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch

@ExperimentalCoroutinesApi
class GetAllDriverWithShipmentsUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var driverRepository: DriverRepository
    private lateinit var shipmentRepository: ShipmentRepository
    private lateinit var assignShipmentsUseCase: AssignShipmentsUseCase
    private lateinit var getAllDriverWithShipmentsUseCase: GetAllDriverWithShipmentsUseCase

    @Before
    fun setup() {
        driverRepository = FakeDriverRepository()
        shipmentRepository = FakeShipmentRepository()
        assignShipmentsUseCase = AssignShipmentsUseCase(driverRepository, shipmentRepository)
        getAllDriverWithShipmentsUseCase = GetAllDriverWithShipmentsUseCase(driverRepository)
    }

    @Test
    fun getAllDriverWithShipment_IsNotEmptyAndAllDriversHaveShipment() {
        mainCoroutineRule.runBlockingTest {
            assignShipmentsUseCase.invoke(Unit)
            val driverWithShipmentList: List<DriverWithShipment> = getAllDriverWithShipmentsUseCase.invoke(Unit).single()
            assertThat(true, IsEqual(driverWithShipmentList.isNotEmpty()))
            assertThat(true, IsEqual(driverWithShipmentList.all { it.shipment != null }))
        }
    }
}