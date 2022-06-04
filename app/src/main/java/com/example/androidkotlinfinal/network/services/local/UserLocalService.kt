package com.example.androidkotlinfinal.network.services.local

import androidx.lifecycle.LiveData
import com.example.androidkotlinfinal.database.dao.UserDao
import com.example.androidkotlinfinal.database.entities.DatabaseUser
import javax.inject.Inject

class UserLocalService @Inject constructor(private val userDao: UserDao) {
    fun getUsers() : LiveData<List<DatabaseUser>>{
        return userDao.getUsers()
    }

    suspend fun getUserDetail(login : String) : DatabaseUser{
        return userDao.getUserDetail(login)
    }

    suspend fun insertUsers(databaseUsers: List<DatabaseUser>){
        return userDao.insertUsers(databaseUsers)
    }

    suspend fun insertUser(databaseUser: DatabaseUser){
        return userDao.insertUser(databaseUser)
    }
}