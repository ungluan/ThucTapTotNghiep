package com.example.androidkotlinfinal.di

import androidx.navigation.Navigation
import com.example.androidkotlinfinal.navigation.AppNavigator
import com.example.androidkotlinfinal.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {
    @Binds
    abstract fun bindNavigation(impl: AppNavigatorImpl): AppNavigator
}