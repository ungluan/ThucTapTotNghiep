package com.example.androidkotlinfinal.network

import com.example.androidkotlinfinal.BuildConfig
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Qualifier

class AccessTokenInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN}")
                .build()
        )
    }
}