package com.example.kotlincoroutine2.remote

import com.example.kotlincoroutine2.model.Movie
import com.example.kotlincoroutine2.model.TmpMovies

interface WebService {
    suspend fun getMoviesByTitle(query: String, page: Int?): TmpMovies

    suspend fun getMovieById(id: String): Movie
}