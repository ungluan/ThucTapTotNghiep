package com.example.androidkotlinfinal.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.androidkotlinfinal.common.Config.AFTER_ID_DEFAULT
import com.example.androidkotlinfinal.common.Config.PAGE_SIZE
import com.example.androidkotlinfinal.database.entities.asDomainModel
import com.example.androidkotlinfinal.domain.User
import com.example.androidkotlinfinal.network.dto.asDatabaseModel
import com.example.androidkotlinfinal.network.services.local.UserLocalService
import com.example.androidkotlinfinal.network.services.remote.UserRemoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * Repository sẽ implement các function của LOCAL_DATA_BASE and REMOTE_SERVICE
 * */
class UserRepository @Inject constructor(
    private val userLocalService: UserLocalService,
    private val userRemoteService: UserRemoteService
) {

    val users: LiveData<List<User>> =
        Transformations.map(userLocalService.getUsers()) { databaseUsers ->
            databaseUsers.asDomainModel()
        }

    suspend fun getUserDatabase(login: String): User {
        Timber.d("Begin Call getUserDatabase:")
        val userDatabase = userLocalService.getUserDetail(login)
        Timber.d("Finished getUserDatabase: $userDatabase")
        return userDatabase.asDomainModel()
    }

    suspend fun refreshUsers() {
        try {
            withContext(Dispatchers.IO) {
                Timber.d("Calling API getUsers")
                val networkUsers = userRemoteService.getListUser(
                    afterId = AFTER_ID_DEFAULT,
                    pageSize = PAGE_SIZE
                )
                Timber.d("ListUser: $networkUsers")
                userLocalService.insertUsers(networkUsers.asDatabaseModel())
                Timber.d("Wrote ListUser to Database")
            }
        } catch (e: Exception) {
            Timber.d("Refresher User failed: ${e.message}")
        } finally {
            Timber.d("Finished Refresh User At: ${Calendar.getInstance().time} ,${Thread.currentThread().name}")
        }
    }

    suspend fun getUserNetwork(login: String) {
        try {
            withContext(Dispatchers.IO){
                Timber.d("Start getUserNetwork At: ${Calendar.getInstance().time} ,${Thread.currentThread().name}")
                val networkUser = userRemoteService.getUser(login)
                Timber.d("User: $networkUser")
                userLocalService.insertUser(networkUser.asDatabaseModel())
                Timber.d("Wrote User to Database")
            }
        } catch (e: Exception) {
            Timber.d("GetUserNetwork failed: ${e.message}")
        } finally {
            Timber.d("Finished Refresh User At: ${Calendar.getInstance().time} ,${Thread.currentThread().name}")
        }
    }
}