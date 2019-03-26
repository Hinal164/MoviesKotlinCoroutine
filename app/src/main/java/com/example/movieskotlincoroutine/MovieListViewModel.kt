package com.example.movieskotlincoroutine

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.movieskotlincoroutine.database.MovieDao
import com.example.movieskotlincoroutine.model.Movie
import javax.inject.Inject

class MovieListViewModel() : ViewModel() {

    private var movieList: LiveData<Resource<List<Movie>>>? = null

    @Inject
    constructor(movieRepository: MovieRepository) : this() {
       movieList=movieRepository.loadMovies()

    }

    fun getMovieList(): LiveData<Resource<List<Movie>>>? {
        return movieList
    }
}