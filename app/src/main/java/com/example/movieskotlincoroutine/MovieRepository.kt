package com.example.movieskotlincoroutine

import android.arch.lifecycle.LiveData
import com.example.movieskotlincoroutine.database.MovieDao
import com.example.movieskotlincoroutine.model.Movie
import com.example.movieskotlincoroutine.network.Webservice
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(var movieDao: MovieDao, var webService: Webservice) {


    fun loadMovies(): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<Movie>>() {

            private fun saveCallResult(item: List<Movie>?) {
                if (item != null)
                    movieDao.save(item)
            }

            private fun shouldFetch(@Nullable data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()

            }

            @NonNull
            private fun loadFromDb(): LiveData<List<Movie>> {
                return movieDao.loadMovies()
            }

            @NonNull
            private fun createCall(): Call<List<Movie>> {
                return webService.getMovies()
            }
        }.getAsLiveData()
    }


}