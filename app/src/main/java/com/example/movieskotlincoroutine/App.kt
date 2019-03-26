package com.example.movieskotlincoroutine

import android.app.Activity
import android.app.Application
import com.example.movieskotlincoroutine.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    private var sInstance: App? = null

    private fun setInstance(app: App) {
        sInstance = app
    }

    override fun onCreate() {
        super.onCreate()
        initializeComponent()
        setInstance(this)

    }

    private fun initializeComponent() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

    }
}