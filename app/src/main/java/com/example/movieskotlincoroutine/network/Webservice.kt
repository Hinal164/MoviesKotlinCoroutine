package com.example.movieskotlincoroutine.network

import com.example.movieskotlincoroutine.model.User
import retrofit2.Call
import retrofit2.http.GET

interface Webservice {

    @GET("/posts")
    fun getUser(): Call<List<User>>
}