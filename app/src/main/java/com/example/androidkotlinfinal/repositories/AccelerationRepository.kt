package com.example.androidkotlinfinal.repositories

import androidx.lifecycle.LiveData
import com.example.androidkotlinfinal.database.dao.AccelerationDao
import com.example.androidkotlinfinal.database.entities.AccelerationData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class AccelerationRepository @Inject constructor(
    private val accelerationDao: AccelerationDao
) {
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.d("Error: ${throwable.message}")
    }

    private val _accelerations = accelerationDao.getAccelerations()
    val accelerations: LiveData<List<AccelerationData>> = _accelerations

    suspend fun insertAccelerationData(accelerationData: AccelerationData){
        withContext(Dispatchers.IO){
            accelerationDao.insertAcceleration(accelerationData)
        }
    }

    suspend fun deleteAllAcceleration(){
        withContext(Dispatchers.IO){
            accelerationDao.deleteAllAcceleration()
        }
    }
}