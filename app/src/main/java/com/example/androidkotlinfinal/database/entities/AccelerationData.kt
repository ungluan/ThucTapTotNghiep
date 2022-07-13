package com.example.androidkotlinfinal.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.math.sqrt

@Entity(tableName = "acceleration_sensor")
data class AccelerationData(
    val x: Float,
    val y: Float,
    val z: Float,
    val threshold: Float = sqrt(x*x+y*y+z+z),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)