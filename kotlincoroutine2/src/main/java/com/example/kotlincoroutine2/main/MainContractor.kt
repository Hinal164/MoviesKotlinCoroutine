package com.example.kotlincoroutine2.main

import com.example.kotlincoroutine2.Utils.bases.IBaseInteractor
import com.example.kotlincoroutine2.Utils.bases.IPresenter
import com.example.kotlincoroutine2.Utils.bases.IView
import com.example.kotlincoroutine2.model.Movie

interface MainContractor {

    interface View : IView<Presenter> {
        fun showMoreMovies(
            movies: List<Movie>,
            isReload: Boolean
        )
    }

    interface Interactor : IBaseInteractor {
        suspend fun getMoviesByTitle(title: String, page: Int?): List<Movie>
    }

    interface Presenter : IPresenter<View> {

        fun onLoadMoviesByTitle(title: String, page: Int, isReload: Boolean)

    }
}