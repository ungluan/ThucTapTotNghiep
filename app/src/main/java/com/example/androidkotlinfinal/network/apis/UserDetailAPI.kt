package com.example.androidkotlinfinal.network.apis

import com.example.androidkotlinfinal.network.models.NetworkUser
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailAPI {
    //https://api.github.com/users/lawrencepit
    @GET("users/{login}")
    suspend fun getUser(
        @Path("login") login: String
    ): NetworkUser
}