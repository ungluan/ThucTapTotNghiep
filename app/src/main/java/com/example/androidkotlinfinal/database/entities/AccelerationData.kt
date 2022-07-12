package com.example.androidkotlinfinal.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "acceleration_sensor")
data class AccelerationData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val x: String,
    val y: String,
    val z: String
)