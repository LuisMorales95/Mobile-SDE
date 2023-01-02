package com.jlmp.mobilesdecodeexercise.presentation.driver

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlmp.domain.DriverWithShipment
import com.jlmp.interactors.AssignShipmentsUseCase
import com.jlmp.interactors.GetAllDriverWithShipmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(
    private val getAllDriverWithShipmentsUseCase: GetAllDriverWithShipmentsUseCase,
    private val assignShipmentsUseCase: AssignShipmentsUseCase
): ViewModel() {

    private var _drivers : MutableLiveData<List<DriverWithShipment>> =  MutableLiveData()
    val drivers: LiveData<List<DriverWithShipment>> = _drivers

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun getDrivers() {
        _dataLoading.value = true
        viewModelScope.launch {
            assignShipmentsUseCase.invoke(Unit)
            getAllDriverWithShipmentsUseCase.invoke(Unit).collect {
                _drivers.postValue(it)
                _dataLoading.value = false
            }
        }
    }
}