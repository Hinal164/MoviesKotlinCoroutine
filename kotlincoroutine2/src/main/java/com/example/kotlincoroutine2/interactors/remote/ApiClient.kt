package com.example.kotlincoroutine2.interactors.remote

import com.example.kotlincoroutine2.Utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        var retrofit:ApiInterface? =null

        fun getClient(): ApiInterface {
            if (retrofit == null) {
                val builder = OkHttpClient.Builder()

              /*  val httpLoggingInterceptor = HttpLoggingInterceptor()

                // Can be Level.BASIC, Level.HEADERS, or Level.BODY
                // See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                builder.networkInterceptors().add(httpLoggingInterceptor)*/

                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(builder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiInterface::class.java)
            }
            return retrofit as ApiInterface
        }
    }
}