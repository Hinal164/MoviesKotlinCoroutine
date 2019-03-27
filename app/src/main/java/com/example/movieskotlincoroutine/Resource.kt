package com.example.movieskotlincoroutine

import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable

class Resource<T> private constructor(
    @param:NonNull @field:NonNull
    val currentState: Status,
    @param:Nullable @field:Nullable
    val data: T,
    @param:Nullable @field:Nullable
    val message: String?) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }


    companion object {

        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, @Nullable data: T): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(@Nullable data: T): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
