package com.example.movieskotlincoroutine.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.movieskotlincoroutine.model.Converters
import com.example.movieskotlincoroutine.model.Movie
import com.example.movieskotlincoroutine.model.User

@Database(entities = [(User::class)], version = 2, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao

}