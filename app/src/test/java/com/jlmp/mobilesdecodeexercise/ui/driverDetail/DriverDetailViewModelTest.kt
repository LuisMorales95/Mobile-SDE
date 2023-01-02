package com.jlmp.mobilesdecodeexercise.ui.driverDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jlmp.domain.repository.DriverRepository
import com.jlmp.domain.repository.ShipmentRepository
import com.jlmp.interactors.AssignShipmentsUseCase
import com.jlmp.interactors.GetDriverWithShipmentsUseCase
import com.jlmp.mobilesdecodeexercise.MainCoroutineRule
import com.jlmp.mobilesdecodeexercise.data.FakeDriverRepository
import com.jlmp.mobilesdecodeexercise.data.FakeShipmentRepository
import com.jlmp.mobilesdecodeexercise.getOrAwaitValue
import com.jlmp.mobilesdecodeexercise.presentation.driverDetails.DriverDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DriverDetailViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    private lateinit var driverRepository: DriverRepository
    private lateinit var shipmentRepository: ShipmentRepository
    private lateinit var assignShipmentsUseCase: AssignShipmentsUseCase
    private lateinit var getDriverWithShipmentsUseCase: GetDriverWithShipmentsUseCase
    private lateinit var driverDetailViewModel: DriverDetailViewModel

    @Before
    fun setup() {
        mainCoroutineRule.launch {
            driverRepository = FakeDriverRepository()
            shipmentRepository = FakeShipmentRepository()
            assignShipmentsUseCase = AssignShipmentsUseCase(driverRepository, shipmentRepository)
            getDriverWithShipmentsUseCase = GetDriverWithShipmentsUseCase(driverRepository)
            driverDetailViewModel = DriverDetailViewModel(getDriverWithShipmentsUseCase)
            assignShipmentsUseCase.invoke(Unit)
        }
    }

    @Test
    fun loadDriver_getDriverWithShipment() = runBlockingTest {
        driverDetailViewModel.getDriver(1L)
        assertThat(
            driverDetailViewModel.driverName.getOrAwaitValue().isNotEmpty(),
            IsEqual(true)
        )
        assertThat(
            driverDetailViewModel.shipmentAddress.getOrAwaitValue().isNotEmpty(),
            IsEqual(true)
        )
    }
}