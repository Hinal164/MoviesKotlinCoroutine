package com.example.movieskotlincoroutine.di

import com.example.movieskotlincoroutine.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}