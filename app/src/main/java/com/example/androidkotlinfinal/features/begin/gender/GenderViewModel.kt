package com.example.androidkotlinfinal.features.begin.gender

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject() constructor() : ViewModel() {
    private val _maleSelected = MutableLiveData<Boolean>(true)
    val maleSelected : LiveData<Boolean> get() = _maleSelected

    fun changeGender(){
        _maleSelected.postValue(!_maleSelected.value!!)
    }
}