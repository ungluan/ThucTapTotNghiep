package com.example.androidkotlinfinal.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.androidkotlinfinal.common.Config.AFTER_ID_DEFAULT
import com.example.androidkotlinfinal.common.Config.PAGE_SIZE
import com.example.androidkotlinfinal.database.entities.DatabaseUser
import com.example.androidkotlinfinal.database.entities.asDomainModel
import com.example.androidkotlinfinal.domain.User
import com.example.androidkotlinfinal.network.dto.asDatabaseModel
import com.example.androidkotlinfinal.network.services.local.UserLocalService
import com.example.androidkotlinfinal.network.services.remote.UserRemoteService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
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

    suspend fun getUserDetail(login: String): User {
        return withContext(Dispatchers.IO) {
            Timber.d("Begin Call getUserDatabase:")
            val userDatabase = userLocalService.getUserDetail(login)
            Timber.d("Finished getUserDatabase: $userDatabase")
            userDatabase.asDomainModel()
        }
    }

    // If try catch, when
//    suspend fun getUser(login: String): User {
//        return withContext(Dispatchers.IO) {
//            Timber.d("Begin Get User")
//            val networkUser = userRemoteService.getUser(login)
//            Timber.d("Get user success full ${networkUser.id}")
//            userLocalService.insertUser(networkUser.asDatabaseModel())
//            val userDatabase = userLocalService.getUserDetail(login)
//            userDatabase.asDomainModel()
//        }
//    }

    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {
            Timber.d("Calling API getUsers")
            val networkUsers = userRemoteService.getListUser(
                afterId = AFTER_ID_DEFAULT,
                pageSize = PAGE_SIZE
            )
            Timber.d("ListUser: $networkUsers")
            Timber.d("ListUserSize: ${networkUsers.size}")
            userLocalService.insertUsers(networkUsers.asDatabaseModel())
            Timber.d("Wrote ListUser to Database")
        }

    }

    suspend fun getUserNetwork(login: String) {
        withContext(Dispatchers.IO) {
            Timber.d("Start getUserNetwork At: ${Calendar.getInstance().time} ,${Thread.currentThread().name}")
            val networkUser = userRemoteService.getUser(login)
            Timber.d("User: $networkUser")
            userLocalService.insertUser(networkUser.asDatabaseModel())
            Timber.d("Wrote User to Database")
        }
    }
}