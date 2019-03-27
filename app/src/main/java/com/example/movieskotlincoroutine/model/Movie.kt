package com.example.movieskotlincoroutine.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters

@Entity
data class Movie (
    @field:PrimaryKey
    var id: Int,
    var title: String,
    var poster: String,
    var year: String,
    var country: String,
    var imdb_rating: String,
    @TypeConverters(Converters::class)
    var genres: List<String>,
    @TypeConverters(Converters::class)
    var images:List<String>


)