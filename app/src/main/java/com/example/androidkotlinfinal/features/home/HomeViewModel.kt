package com.example.androidkotlinfinal.features.home

import androidx.lifecycle.*
import com.example.androidkotlinfinal.database.entities.AccelerationData
import com.example.androidkotlinfinal.repositories.AccelerationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accelerationRepository: AccelerationRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.d("Error: ${throwable.message}")
    }

    private val _accelerations = accelerationRepository.accelerations
    val accelerations: LiveData<List<AccelerationData>>
        get() = _accelerations
}