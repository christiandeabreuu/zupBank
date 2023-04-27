package com.zup.authenticator.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ValidViewModel : ViewModel() {

    private val _validUiState = MutableStateFlow<Resource<Boolean>>(Resource.Initialize())
    val validUiState: StateFlow<Resource<Boolean>> = _validUiState

    private val _listInt = MutableStateFlow<Resource<Boolean>>(Resource.Initialize())
    val listInt: StateFlow<Resource<Boolean>> = _listInt

    fun checkoutNumbers(list: List<Int>, listNumbers: List<Int>): Boolean {
        return listNumbers.equals(list)
    }

    fun createPin(): MutableList<Int> {
        val listNumbers = mutableListOf<Int>()
        for (i in 1..4) {
            var numberRandom = (0..9).random()
            listNumbers.add(numberRandom)
        }
        return listNumbers
    }
//    fun handleCallback(list: List<Int>) {
//        _validUiState.value = Resource.
//    }
}
