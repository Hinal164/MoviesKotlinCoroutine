package com.example.movieskotlincoroutine

import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable

class Resource<T>(var status: Status, private var data: T, private var message: String?) {

        fun getCurrentState(): Status {

            return status
        }

        fun getMovieData(): T {
            return data
        }

        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, @Nullable data: T): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }


        fun <T> loading(@Nullable data: T): Resource<T>? {
            return Resource(Status.LOADING, data, null)
        }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }
}