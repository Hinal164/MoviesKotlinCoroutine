package com.example.kotlincoroutine2.main

import com.example.kotlincoroutine2.model.Movie

interface MainInteractor {
    suspend fun getMoviesByTitle(title: String, page: Int?): List<Movie>

    suspend fun getMovieByID(id: String): Movie
}