package com.example.movieskotlincoroutine.di

import android.app.Application
import com.example.movieskotlincoroutine.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (AndroidInjectionModule::class), (ActivityBuilderModule::class), (ViewModelModule::class)])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}