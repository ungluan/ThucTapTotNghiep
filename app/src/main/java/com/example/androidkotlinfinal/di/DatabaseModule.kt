package com.example.androidkotlinfinal.di

import android.content.Context
import androidx.room.Room
import com.example.androidkotlinfinal.common.Config.DATABASE_NAME
import com.example.androidkotlinfinal.database.AppDatabase
import com.example.androidkotlinfinal.database.dao.AccelerationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAccelerationDao(database: AppDatabase): AccelerationDao {
        return database.accelerationDao
    }
}