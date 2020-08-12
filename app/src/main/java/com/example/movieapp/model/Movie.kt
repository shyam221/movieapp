package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val title: String,
    val overview: String,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("genre_ids") val genre_ids: Array<Int>
)