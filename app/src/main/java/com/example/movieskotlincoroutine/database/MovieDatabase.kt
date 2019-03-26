package com.example.movieskotlincoroutine.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.movieskotlincoroutine.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase:RoomDatabase() {
    abstract fun movieDao(): MovieDao
}