package com.example.androidkotlinfinal.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidkotlinfinal.database.entities.DatabaseUser
import kotlinx.coroutines.Deferred

@Dao
interface UserDao {
    @Query("Select * from user")
    fun getUsers() : LiveData<List<DatabaseUser>>

    @Query("Select * from user where login = :loginValue")
    suspend fun getUserDetail(loginValue : String) : DatabaseUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(databaseUsers: List<DatabaseUser>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: DatabaseUser)
}