package com.example.kotlincoroutine2.Utils.bases

interface IPresenter<T> {
    fun subscribe()

    fun unsubscribe()

    /**
     * This method will be invoked during [android.app.Activity.onStart], [android.app.Fragment.onResume]
     * and [android.view.View.onAttachedToWindow].
     */
    fun onViewAttached(view: T)

}