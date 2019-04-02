package com.example.kotlincoroutine2

import io.reactivex.Observer
import io.reactivex.observers.DisposableObserver

class Listener<T> : DisposableObserver<T>(), Observer<T> {

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }

    override fun onComplete() {

    }

    override fun onNext(t: T) {

    }

}