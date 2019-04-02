package com.example.kotlincoroutine2.di

import android.app.Application
import com.example.kotlincoroutine2.BuildConfig
import com.example.kotlincoroutine2.Utils.Constants
import com.example.kotlincoroutine2.Utils.DispatcherProvider
import com.example.kotlincoroutine2.Utils.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class AppModule(var app: Application) {

    /*companion object {

        @JvmStatic
        @Provides
        @Singleton
        @Named("isDebug")
        fun provideIsDebug(): Boolean {
            return BuildConfig.DEBUG
        }

        @JvmStatic
        @Provides
        @Singleton
        @Named("networkTimeoutInSeconds")
        fun provideNetworkTimeoutInSeconds(): Int {
            return 30
        }

        @JvmStatic
        @Provides
        @Singleton
        @Named("BaseUrl")
        fun provideBaseUrl(): String {
            return Constants.BASE_URL
        }
    }*/

    @Binds
    @Singleton
    abstract fun provideAppDispatcher(schedulerProvider: DispatcherProviderImpl): DispatcherProvider

}