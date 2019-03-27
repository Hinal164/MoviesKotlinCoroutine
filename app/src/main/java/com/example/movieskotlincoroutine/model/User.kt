package com.example.movieskotlincoroutine.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    var userId: Int,
    @PrimaryKey
    var id: Int,
    var title: String,
    var body: String
)
