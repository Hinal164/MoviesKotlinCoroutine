package com.example.movieskotlincoroutine.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.movieskotlincoroutine.UserListViewModel
import com.example.movieskotlincoroutine.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindsMovieListViewModel(movieListViewModel: UserListViewModel): ViewModel


    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}