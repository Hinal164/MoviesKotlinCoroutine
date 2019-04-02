package com.example.kotlincoroutine2.main

import com.example.kotlincoroutine2.Utils.DispatcherProvider
import com.example.kotlincoroutine2.remote.WebService
import com.example.kotlincoroutine2.model.Movie
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MovieInteractor @Inject
constructor(private val webService: WebService, private val dispatcherProvider: DispatcherProvider) :
    MainContractor.Interactor {

    override suspend fun getMoviesByTitle(title: String, page: Int?): List<Movie> = runBlocking {
        return@runBlocking async(dispatcherProvider.backgroundDispatcher()) {
            webService.getMoviesByTitle(title, page).data
        }.await()
    }

    /*override suspend fun getMovieByID(id: String): Movie {
        return withContext(context = dispatcherProvider.backgroundDispatcher()) {
            webService.getMovieById(id)
        }
    }*/
    override fun unsubscribe() {

    }

}
