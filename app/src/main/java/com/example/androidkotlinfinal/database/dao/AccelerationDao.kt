package com.example.androidkotlinfinal.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidkotlinfinal.database.entities.AccelerationData

@Dao
interface AccelerationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAcceleration(accelerationData: AccelerationData)

    @Query("Select * from acceleration_sensor")
    fun getAccelerations(): LiveData<List<AccelerationData>>
}