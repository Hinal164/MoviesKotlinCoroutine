package com.example.movieskotlincoroutine.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.movieskotlincoroutine.model.Movie
import com.example.movieskotlincoroutine.model.TmpMovies

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(item: TmpMovies)


    @Query("SELECT * FROM movie ")
    fun loadMovies(): LiveData<TmpMovies>
}