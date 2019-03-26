package com.example.movieskotlincoroutine.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass



@MustBeDocumented
@kotlin.annotation.Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)