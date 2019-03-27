package com.example.movieskotlincoroutine

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.movieskotlincoroutine.model.User
import javax.inject.Inject

class UserListViewModel() : ViewModel() {

    private var userList: LiveData<Resource<List<User>>>? = null

    @Inject
    constructor(userRepository: UserRepository) : this() {
       userList=userRepository.loadUsers()

    }

    fun getMovieList(): LiveData<Resource<List<User>>>? {
        return userList
    }
}