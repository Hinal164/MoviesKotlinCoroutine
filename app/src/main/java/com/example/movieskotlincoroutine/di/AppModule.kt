package com.example.movieskotlincoroutine.di

import android.app.Application
import android.arch.persistence.room.Room
import com.example.movieskotlincoroutine.ApiConstants
import com.example.movieskotlincoroutine.database.UserDao
import com.example.movieskotlincoroutine.database.UserDatabase
import com.example.movieskotlincoroutine.network.Webservice
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Webservice {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(Webservice::class.java)
    }

    @Provides
    @Singleton
    internal fun provideMovieDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(application, UserDatabase::class.java, "users.db").build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }
}