package com.example.movieskotlincoroutine

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread

class NetworkBoundResource<T,V> {

    private val result: MediatorLiveData<Resource<T>>? = null


    @MainThread
    constructor(){
        result.setValue(Resource.loading(null))

        // Always load the data from DB intially so that we have
        val dbSource:LiveData<T> = loadFromDb()

        // Fetch the data from network and add it to the resource
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    if (null != newData)
                        result.setValue(Resource.success(newData))
                }
            }
        }
    }

}