package com.example.kotlincoroutine2.Utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.newFixedThreadPoolContext
import javax.inject.Inject

class DispatcherProviderImpl @Inject
constructor() : DispatcherProvider {

    private var pool = newFixedThreadPoolContext(2 * Runtime.getRuntime().availableProcessors(), "bg")

    override fun backgroundDispatcher(): CoroutineDispatcher {
        return pool
    }
}