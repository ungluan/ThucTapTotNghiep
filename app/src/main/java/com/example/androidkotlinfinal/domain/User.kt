package com.example.androidkotlinfinal.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class User @Inject constructor (
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String,
    val name: String?,
    val blog: String?,
    val company: String?,
    val createdAt: String?,
    val email: String?,
    val followers: Int?,
    val bio: String?,
    val location: String?
) : Parcelable