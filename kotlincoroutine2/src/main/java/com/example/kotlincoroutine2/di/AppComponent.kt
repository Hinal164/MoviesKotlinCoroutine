package com.example.kotlincoroutine2.di

import com.example.kotlincoroutine2.App
import com.example.kotlincoroutine2.main.MainModule
import com.example.kotlincoroutine2.main.MainSubComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidModule::class, AppModule::class, ApiModule::class, InteractorModule::class])
interface AppComponent {

    fun plus(mainModule: MainModule): MainSubComponent

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: App): Builder
    }
}