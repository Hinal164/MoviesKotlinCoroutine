package com.example.movieskotlincoroutine.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.movieskotlincoroutine.MovieListViewModel
import com.example.movieskotlincoroutine.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity()  {

    private var viewModel: MovieListViewModel?=null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel=ViewModelProviders.of(this,viewModelFactory).get(MovieListViewModel::class.java)



    }
}
