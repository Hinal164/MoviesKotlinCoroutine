package com.example.kotlincoroutine2.di

import com.example.kotlincoroutine2.interactors.MovieInteractor
import com.example.kotlincoroutine2.interactors.MovieInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class InteractorModule {

    @Binds
    abstract fun provideMovieInteractor(interactor: MovieInteractorImpl): MovieInteractor
}