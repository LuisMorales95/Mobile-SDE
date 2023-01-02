package com.jlmp.mobilesdecodeexercise.ui.driver

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jlmp.domain.repository.DriverRepository
import com.jlmp.domain.repository.ShipmentRepository
import com.jlmp.interactors.AssignShipmentsUseCase
import com.jlmp.interactors.GetAllDriverWithShipmentsUseCase
import com.jlmp.mobilesdecodeexercise.MainCoroutineRule
import com.jlmp.mobilesdecodeexercise.data.FakeDriverRepository
import com.jlmp.mobilesdecodeexercise.data.FakeShipmentRepository
import com.jlmp.mobilesdecodeexercise.getOrAwaitValue
import com.jlmp.mobilesdecodeexercise.presentation.driver.DriverViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DriverViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    private lateinit var driverRepository: DriverRepository
    private lateinit var shipmentRepository: ShipmentRepository
    private lateinit var assignShipmentsUseCase: AssignShipmentsUseCase
    private lateinit var getAllDriverWithShipmentsUseCase: GetAllDriverWithShipmentsUseCase
    private lateinit var driverViewModel: DriverViewModel

    @Before
    fun setup() {
        driverRepository = FakeDriverRepository()
        shipmentRepository = FakeShipmentRepository()
        assignShipmentsUseCase = AssignShipmentsUseCase(driverRepository, shipmentRepository)
        getAllDriverWithShipmentsUseCase = GetAllDriverWithShipmentsUseCase(driverRepository)
        driverViewModel = DriverViewModel(getAllDriverWithShipmentsUseCase, assignShipmentsUseCase)
    }

    @Test
    fun loadDrivers_loading() {
        mainCoroutineRule.pauseDispatcher()
        driverViewModel.getDrivers()
        assertThat(driverViewModel.dataLoading.getOrAwaitValue(), IsEqual(true))
        mainCoroutineRule.resumeDispatcher()
        assertThat(driverViewModel.dataLoading.getOrAwaitValue(), IsEqual(false))
    }

    @Test
    fun loadDrivers_getAllDrivers() {
        driverViewModel.getDrivers()
        assertThat(driverViewModel.drivers.getOrAwaitValue().isNotEmpty(), IsEqual(true))
    }
}