package com.jlmp.mobilesdecodeexercise.presentation.driverDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlmp.domain.DriverWithShipment
import com.jlmp.interactors.GetDriverWithShipmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverDetailViewModel @Inject constructor(
    private val getDriverWithShipmentsUseCase: GetDriverWithShipmentsUseCase
): ViewModel() {

    private var _driversWithShipment : MutableLiveData<DriverWithShipment> =  MutableLiveData()

    val driverName : LiveData<String> = MediatorLiveData<String>().apply {
        addSource(_driversWithShipment) {
            postValue(it.driver.name)
        }
    }

    val shipmentAddress : LiveData<String> = MediatorLiveData<String>().apply {
        addSource(_driversWithShipment) {
            postValue(it.shipment?.address ?: "")
        }
    }

    fun getDriver(driverId: Long) {
        viewModelScope.launch {
            val driverWithShipments = getDriverWithShipmentsUseCase.invoke(driverId)
            _driversWithShipment.postValue(driverWithShipments)
        }
    }
}