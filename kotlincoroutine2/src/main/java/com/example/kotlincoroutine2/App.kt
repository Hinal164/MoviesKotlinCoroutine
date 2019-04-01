package com.example.kotlincoroutine2

import android.app.Application
import com.example.kotlincoroutine2.di.AppComponent
import com.example.kotlincoroutine2.di.DaggerAppComponent

class App : Application() {

    companion object {
        private var component: AppComponent? = null
        fun getComponent(): AppComponent? {
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .application(this)
            .build()

    }
}