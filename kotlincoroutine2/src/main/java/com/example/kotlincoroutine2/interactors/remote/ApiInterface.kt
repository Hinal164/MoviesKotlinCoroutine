package com.example.kotlincoroutine2.interactors.remote

import com.example.kotlincoroutine2.model.Movie
import com.example.kotlincoroutine2.model.TmpMovies
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    //http://moviesapi.ir/api/v1/movies?q=[QUERY]
    @GET("movies")
    fun getMoviesByTitle(@Query("q") query: String, @Query("page") page: Int?): Deferred<TmpMovies>

    //http://moviesapi.ir/api/v1/movies/{ID}
    @GET("movies/{id}")
    fun getMovieById(@Path("id") id: String): Deferred<Movie>
}