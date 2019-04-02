package com.example.kotlincoroutine2.remote

import retrofit2.Response
import java.lang.Exception

class GeneralApiException constructor(var response: Response<*>) :Exception() {

    override var message: String? = null
    var errors: String? = null


    /**
     * HTTP status code.
     */
    fun code(): Int {
        return response.code()
    }

    /**
     * HTTP status message.
     */
    fun message(): String? {
        return message
    }

    /**
     * The full HTTP response. This may be null if the exception was serialized.
     */
    fun response(): Response<*> {
        return response
    }
}