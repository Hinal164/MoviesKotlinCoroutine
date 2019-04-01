package com.example.kotlincoroutine2.main

import android.util.Log
import com.example.kotlincoroutine2.interactors.MovieInteractor
import com.example.kotlincoroutine2.interactors.remote.GeneralApiException
import javax.inject.Inject
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.lang.Exception

class MainPresenterImpl @Inject constructor(private val interactor: MovieInteractor): MainContractor.Presenter{

    private lateinit var view:MainContractor.View
    private val compositeDisposable= mutableListOf<Job>()

    override fun onSearchButtonClick(terms: String) {

    }


    override fun onLoadMoviesByTitle(title: String, page: Int) = runBlocking<Unit> {
        val job=launch {
            try{
                val movies=interactor.getMoviesByTitle(title,page)
                view.showMoreMovies(movies)
            }catch (e:Exception){
                when (e) {
                    is HttpException -> Log.d("TAG", "onError StatusCode: " + e.code())
                    is GeneralApiException -> Log.d("TAG", "onError message: " + e.message())
                    else -> Log.d("TAG", "onError")
                }
            }
        }
        compositeDisposable.add(job)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun onViewAttached(view: MainContractor.View) {
        this.view=view
    }


}