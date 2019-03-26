package com.example.movieskotlincoroutine.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Movie (
    @field:PrimaryKey
    var id: Int,
    var title: String,
    var poster: String
   /* var year: String,
    var country: String,
    var imdb_rating: String,
    var genres: List<String>,
    var images:List<String>,
    var rated: String,
    var released: String,
    var runtime: String,
    var director: String,
    var writer: String,
    var actors: String,
    var plot: String,
    var awards: String,
    var metascore: String,
    var imdb_votes: String,
    var imdb_id: String,
    var type: String*/
)