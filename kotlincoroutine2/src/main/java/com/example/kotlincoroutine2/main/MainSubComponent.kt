package com.example.kotlincoroutine2.main

import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainSubComponent {
    fun inject(mainActivity: MainActivity)
}