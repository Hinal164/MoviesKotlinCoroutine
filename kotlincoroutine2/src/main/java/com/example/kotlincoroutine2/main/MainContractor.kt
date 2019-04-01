package com.example.kotlincoroutine2.main

import com.example.kotlincoroutine2.Utils.bases.IPresenter
import com.example.kotlincoroutine2.Utils.bases.IView
import com.example.kotlincoroutine2.model.Movie

interface MainContractor {

    interface View : IView<Presenter> {
        fun showMoreMovies(movies: List<Movie>)

        fun clearMovies()

        fun showLoadingForMovies()

        fun hideLoadingForMovies()

        // fun setPresenter(presenter: Presenter)

    }

    interface Presenter : IPresenter<View> {
        fun onSearchButtonClick(terms: String)

        fun onLoadMoviesByTitle(title: String, page: Int)

    }
}