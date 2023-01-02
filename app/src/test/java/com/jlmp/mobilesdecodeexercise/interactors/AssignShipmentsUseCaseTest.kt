package com.jlmp.mobilesdecodeexercise.interactors

import com.jlmp.domain.highestScore
import com.jlmp.domain.repository.DriverRepository
import com.jlmp.domain.repository.ShipmentRepository
import com.jlmp.interactors.AssignShipmentsUseCase
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
class AssignShipmentsUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var driverRepository: DriverRepository
    private lateinit var shipmentRepository: ShipmentRepository
    private lateinit var assignShipmentsUseCase: AssignShipmentsUseCase

    @Before
    fun setup() {
        driverRepository = FakeDriverRepository()
        shipmentRepository = FakeShipmentRepository()
        assignShipmentsUseCase = AssignShipmentsUseCase(driverRepository, shipmentRepository)
    }

    @Test
    fun assignShipments_validateAllHaveShipments() {
        mainCoroutineRule.runBlockingTest {
            assignShipmentsUseCase.invoke(Unit)
            assertThat(true, IsEqual(driverRepository.getDrivers().all { it.idShipment != 0L }))
        }
    }

    @Test
    fun getDriver_even_highestScore() {
        mainCoroutineRule.runBlockingTest {
            val driver = driverRepository.getDrivers().highestScore(true, 0)
            assertThat(4L, IsEqual(driver.id))
        }
    }

    @Test
    fun getDriver_odd_highestScore() {
        mainCoroutineRule.runBlockingTest {
            val driver = driverRepository.getDrivers().highestScore(false, 0)
            assertThat(3L, IsEqual(driver.id))
        }
    }
}