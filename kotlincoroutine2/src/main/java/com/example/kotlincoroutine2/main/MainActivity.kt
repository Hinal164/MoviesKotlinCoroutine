package com.example.kotlincoroutine2.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlincoroutine2.App
import com.example.kotlincoroutine2.R
import com.example.kotlincoroutine2.model.Movie
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContractor.View {


    @Inject
    @JvmField
    var presenter: MainContractor.Presenter? =null
    private var title = ""
    private var current_Page = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        App.getComponent()!!.plus(MainModule()).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter!!.onViewAttached(this)
        presenter!!.onLoadMoviesByTitle(title, 1)

    }

    override fun showMoreMovies(movies: List<Movie>) {
        Log.d("list", "" + movies)
    }

    override fun clearMovies() {
    }

    override fun showLoadingForMovies() {
    }

    override fun hideLoadingForMovies() {
    }

    override fun showToast(txt: String) {
    }
}
