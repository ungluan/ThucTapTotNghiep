package com.example.androidkotlinfinal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidkotlinfinal.database.dao.AccelerationDao
import com.example.androidkotlinfinal.database.entities.AccelerationData

@Database(entities = [AccelerationData::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val accelerationDao: AccelerationDao
}