package com.jlmp.mobilesdecodeexercise.presentation.driver

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jlmp.domain.Driver
import com.jlmp.domain.DriverWithShipment
import com.jlmp.interactors.AssignShipments
import com.jlmp.interactors.GetAllDriverWithShipments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(
   private val getAllDriverWithShipments: GetAllDriverWithShipments,
   private val assignShipments: AssignShipments
): ViewModel() {

    private var _drivers : MutableLiveData<List<DriverWithShipment>> =  MutableLiveData()
    val drivers: LiveData<List<DriverWithShipment>> = _drivers

    init {
        viewModelScope.launch {
            assignShipments.invoke(Unit)
            getAllDriverWithShipments.invoke(Unit).collect {
                _drivers.postValue(it)
            }
        }
    }
}