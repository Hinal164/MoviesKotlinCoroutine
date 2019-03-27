package com.example.movieskotlincoroutine.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.movieskotlincoroutine.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun save(users: List<User>)

    @Query("SELECT * FROM users WHERE id = :id")
     fun load(id: Int): LiveData<User>


    @Query("SELECT * FROM users ")
     fun loadUsers(): LiveData<List<User>>
}