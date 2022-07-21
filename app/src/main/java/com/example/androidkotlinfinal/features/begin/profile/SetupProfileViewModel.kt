package com.example.androidkotlinfinal.features.begin.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SetupProfileViewModel @Inject constructor() : ViewModel() {
    private val _isEnable = MutableLiveData(false)
    val isEnable: LiveData<Boolean> get() = _isEnable

    fun checkEnable(height: String, weight: String){
        val newValue = height.isNotEmpty() && weight.isNotEmpty()
        if(_isEnable.value != newValue){
            _isEnable.postValue(newValue)
        }
    }
}