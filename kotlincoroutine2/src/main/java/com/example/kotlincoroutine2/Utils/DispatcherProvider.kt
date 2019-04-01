package com.example.kotlincoroutine2.Utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

     fun backgroundDispatcher(): CoroutineDispatcher

}