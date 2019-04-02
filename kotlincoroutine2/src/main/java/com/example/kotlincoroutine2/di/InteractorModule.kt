package com.example.kotlincoroutine2.di

import com.example.kotlincoroutine2.main.MovieInteractor
import com.example.kotlincoroutine2.main.MainContractor
import dagger.Binds
import dagger.Module

@Module
abstract class InteractorModule {

    @Binds
    abstract fun provideMovieInteractor(interactor: MovieInteractor): MainContractor.Interactor
}