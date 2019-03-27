package com.example.movieskotlincoroutine.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.movieskotlincoroutine.UserListViewModel
import com.example.movieskotlincoroutine.Resource
import com.example.movieskotlincoroutine.model.User
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private var viewModel: UserListViewModel? = null
    private var users: MutableList<User>? =null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(com.example.movieskotlincoroutine.R.layout.activity_main)


        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)
        println(viewModel)
        viewModel!!.getMovieList()!!.observe(this,
            Observer<Resource<List<User>>> { resource ->

                when (resource?.currentState) {
                    Resource.Status.LOADING -> {
                        val movieList = resource.data
                        if (movieList != null) {
                            users!!.addAll(movieList)
                            println(users)
                            //adapter.notifyDataSetChanged()
                        }
                    }
                    Resource.Status.SUCCESS -> {
                        users!!.addAll(resource.data)
                        Log.d("list",""+users)
                        // adapter.notifyDataSetChanged()
                    }
                    Resource.Status.ERROR -> {
                        println("Error while retrieving the data")
                    }
                }
            })
    }

}
