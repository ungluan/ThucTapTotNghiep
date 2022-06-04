package com.example.androidkotlinfinal.network.apis

import com.example.androidkotlinfinal.network.models.NetworkUser
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersAPI {
    //https://api.github.com/users?since=2&per_page=10
    @GET("/users")
    suspend fun getListUser(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): List<NetworkUser>
}