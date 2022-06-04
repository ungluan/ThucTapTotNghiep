package com.example.androidkotlinfinal.network.services.remote

import com.example.androidkotlinfinal.network.apis.UserDetailAPI
import com.example.androidkotlinfinal.network.apis.UsersAPI
import com.example.androidkotlinfinal.network.models.NetworkUser
import javax.inject.Inject

/**
 *  Remote Service là nơi implement APIs và trả về response
 *
 * */
class UserRemoteService @Inject constructor(
    private val usersAPI: UsersAPI,
    private val userDetailAPI: UserDetailAPI) {

    suspend fun getUser(login: String): NetworkUser {
        return userDetailAPI.getUser(login)
    }

    suspend fun getListUser(afterId: Int, pageSize: Int) : List<NetworkUser> {
        return usersAPI.getListUser(afterId, pageSize)
    }
}