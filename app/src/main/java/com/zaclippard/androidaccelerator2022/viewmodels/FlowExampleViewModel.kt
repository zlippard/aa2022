package com.zaclippard.androidaccelerator2022.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class FlowExampleViewModel : ViewModel() {

    class Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FlowExampleViewModel() as T
        }
    }

    private var flowNumber = 0
    private var stateFlowNumber = 0

    // Flow objects
    val numbersFlow = flow {
        while (true) {
            emit(flowNumber++)
            delay(1_000L)
        }
    }
    val numbersStateFlow = MutableStateFlow(0)

    // LiveData objects
    val numbersLiveData = numbersFlow.asLiveData()
    val numbersStateLiveData = numbersStateFlow.asLiveData()

    fun startStateFlow() {
        viewModelScope.launch {
            while (isActive) {
                delay(1_000L)
                numbersStateFlow.emit(stateFlowNumber++)
            }
        }
    }
}
