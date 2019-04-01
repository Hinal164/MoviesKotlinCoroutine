package com.example.kotlincoroutine2.main

import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun providePresenter(presenter: MainPresenterImpl): MainContractor.Presenter {
        return presenter
    }
}