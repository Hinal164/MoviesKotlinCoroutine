package com.example.movieskotlincoroutine.network

import com.example.movieskotlincoroutine.model.Movie
import com.example.movieskotlincoroutine.model.TmpMovies
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Webservice {
    //http://moviesapi.ir/api/v1/movies?q=[QUERY]
    @GET("movies")
    abstract fun getMoviesByTitle(@Query("q") query: String, @Query("page") page: Int?): Deferred<TmpMovies>

    //http://moviesapi.ir/api/v1/movies/{ID}
    @GET("movies/{id}")
    abstract fun getMovieById(@Path("id") id: String): Deferred<Movie>
}