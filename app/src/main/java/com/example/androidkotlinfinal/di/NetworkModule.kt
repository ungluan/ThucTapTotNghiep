package com.example.androidkotlinfinal.di

import com.example.androidkotlinfinal.common.Config.BASE_GITHUB_URL
import com.example.androidkotlinfinal.network.AccessTokenInterceptor
import com.example.androidkotlinfinal.network.apis.UserDetailAPI
import com.example.androidkotlinfinal.network.apis.UsersAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = AccessTokenInterceptor()


    @Provides
    @Singleton
    fun provideOkhttpClient(
        accessTokenInterceptor: AccessTokenInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(accessTokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideRetrofitGithub(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(BASE_GITHUB_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideUsersAPI(retrofit: Retrofit): UsersAPI {
        return retrofit.create(UsersAPI::class.java)
    }

    @Provides
    fun provideUserDetailAPI(retrofit: Retrofit): UserDetailAPI {
        return retrofit.create(UserDetailAPI::class.java)
    }
}