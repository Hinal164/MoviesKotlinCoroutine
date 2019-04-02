package com.example.kotlincoroutine2.remote

import com.example.kotlincoroutine2.model.Movie
import com.example.kotlincoroutine2.model.TmpMovies
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import java.io.IOException

class MoviesApiServiceImpl (private val api: ApiInterface) : WebService {
    override suspend fun getMoviesByTitle(query: String, page: Int?): TmpMovies {
        return api.getMoviesByTitleAsync(query, page).awaitWithParseError()
    }

    override suspend fun getMovieById(id: String): Movie {
        return api.getMovieByIdAsync(id).awaitWithParseError()
    }

    private suspend fun <R> Deferred<R>.awaitWithParseError(): R {

        try {
            return await()
        } catch (throwable: Exception) {

            if (throwable is HttpException) {

                val gson = com.google.gson.Gson()
                try {
                    val generalApiException = gson.fromJson(throwable.response().errorBody()!!.string(), GeneralApiException::class.java)
                    throw generalApiException//invokes observable onError
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            // if not the kind we're interested in, then just report the same error to onError
            throw throwable//invokes observable onError
        }
    }
}