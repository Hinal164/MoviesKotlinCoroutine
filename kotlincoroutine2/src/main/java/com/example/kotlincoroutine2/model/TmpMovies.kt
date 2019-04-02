package com.example.kotlincoroutine2.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList
import kotlin.Metadata

class TmpMovies {
    var data: List<Movie> = arrayListOf()
    @SerializedName("metadata")
    var metadata: Metadata1? = null
}