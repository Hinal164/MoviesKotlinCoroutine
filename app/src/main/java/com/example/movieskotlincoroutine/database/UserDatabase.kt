package com.example.movieskotlincoroutine.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.movieskotlincoroutine.model.User

@Database(entities = [(User::class)], version = 2, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao

}