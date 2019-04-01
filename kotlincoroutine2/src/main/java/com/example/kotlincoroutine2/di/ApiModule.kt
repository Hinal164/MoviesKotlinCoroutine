package com.example.kotlincoroutine2.di

import android.provider.SyncStateContract
import com.example.kotlincoroutine2.Utils.Constants
import com.example.kotlincoroutine2.interactors.remote.ApiInterface
import com.example.kotlincoroutine2.interactors.remote.MoviesApiServiceImpl
import com.example.kotlincoroutine2.interactors.remote.WebService
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideSearchMoviesApiService(retrofit: Retrofit): WebService {
        return MoviesApiServiceImpl(retrofit.create(ApiInterface::class.java))
    }

    @Provides
    @Singleton
    fun provideRetrofit(
    /*    @Named("BaseUrl") baseUrl: String,*/ converterFactory: Converter.Factory
        /*okHttpClient: OkHttpClient*/
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
           // .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }


}