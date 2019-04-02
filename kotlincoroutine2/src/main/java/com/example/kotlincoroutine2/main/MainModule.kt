package com.example.kotlincoroutine2.main

import dagger.Module
import dagger.Provides

@Module
class MainModule(private var activity: MainActivity) {

    @Provides
    fun providePresenter(interactorImpl: MovieInteractor): MainContractor.Presenter {
        return MainPresenterImpl(activity, interactorImpl)
    }

    @Provides
    fun providesView(): MainContractor.View {
        return activity
    }

}