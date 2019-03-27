package com.example.movieskotlincoroutine.network

import com.example.movieskotlincoroutine.model.Movie
import com.example.movieskotlincoroutine.model.TmpMovies
import com.example.movieskotlincoroutine.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Webservice {
    /*//http://moviesapi.ir/api/v1/movies?q=[QUERY]
    @GET("movies")
    fun getMoviesByTitle(*//*@Query("q") query: String, @Query("page") page: Int?*//*): Call<TmpMovies>

    //http://moviesapi.ir/api/v1/movies/{ID}
    @GET("movies/{id}")
    fun getMovieById(@Path("id") id: String): Deferred<Movie>*/

    @GET("/posts")
    fun getUser(): Call<List<User>>
}