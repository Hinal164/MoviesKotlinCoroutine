package com.example.movieskotlincoroutine

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.example.movieskotlincoroutine.database.UserDao
import com.example.movieskotlincoroutine.model.User
import com.example.movieskotlincoroutine.network.Webservice
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(var userDao: UserDao, var webService: Webservice) {

    fun loadUsers(): LiveData<Resource<List<User>>> {
        return object : NetworkBoundResource<List<User>, List<User>>() {
            override fun saveCallResult(item: List<User>) {
                if (item != null)
                    userDao.save(item)
            }

            override fun shouldFetch(data: List<User>): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<User>> {
                return userDao.loadUsers()
            }

            override fun createCall(): Call<List<User>> {
                return webService.getUser()
            }
        }.getAsLiveData()
    }

}