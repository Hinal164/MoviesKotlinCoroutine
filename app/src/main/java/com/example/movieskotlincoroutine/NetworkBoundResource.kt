package com.example.movieskotlincoroutine

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.AsyncTask
import android.support.annotation.MainThread
import android.support.annotation.NonNull
import android.support.annotation.WorkerThread
import com.example.movieskotlincoroutine.model.TmpMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class NetworkBoundResource<T, V> {

    // lateinit var result: MediatorLiveData<Resource<T>>
    private var result: MediatorLiveData<Resource<T>> = MediatorLiveData()

    @MainThread
    constructor() {

        // result.setValue(Resource.loading(null))

        // Always load the data from DB intially so that we have
        val dbSource: LiveData<T> = loadFromDb()

        // Fetch the data from network and add it to the resource
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data!!)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    if (null != newData) {
                        result.value = Resource.success(newData)
                    }
                }
            }
        }

    }

    private fun fetchFromNetwork(dbSource: LiveData<T>) {

        result.addSource(dbSource) { newData ->
            //if (null != newData) {
                result.value = Resource.loading(newData)
           // }
        }

        createCall().enqueue(object : Callback<V> {
            override fun onResponse(@NonNull call: Call<V>, @NonNull response: Response<V>) {
                result.removeSource(dbSource)
                saveResultAndReInit(response.body()!!)
            }

            override fun onFailure(@NonNull call: Call<V>, @NonNull t: Throwable) {
                result.removeSource(dbSource)
                result.addSource(dbSource) { newData ->
                    if (null != newData) {
                        result.value = Resource.error(t.message!!, newData)
                    }
                }
            }
        })
    }


    @SuppressLint("StaticFieldLeak")
    @MainThread
    private fun saveResultAndReInit(response: V) {
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                saveCallResult(response)
                return null
            }

            override fun onPostExecute(aVoid: Void) {
                result.addSource(loadFromDb()) { newData ->
                    if (null != newData)
                        result.value = Resource.success(newData)
                }
            }
        }.execute()
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: V)

    protected abstract fun shouldFetch(data: T): Boolean

    @NonNull
    @MainThread
    protected abstract fun loadFromDb(): LiveData<T>

    @NonNull
    @MainThread
    protected abstract fun createCall(): Call<V>

    fun getAsLiveData(): LiveData<Resource<T>> {
        return result
    }

}

