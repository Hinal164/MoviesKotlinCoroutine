package com.example.kotlincoroutine2.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import com.example.kotlincoroutine2.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule {

    @Provides
    @Singleton
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideResources(application: App): Resources {
        return application.resources
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(application: App): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}