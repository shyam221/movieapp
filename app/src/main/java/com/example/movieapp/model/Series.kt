package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("name") val name: String,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("first_air_date") val first_air_date: String,
    @SerializedName("genre_ids") val genre_ids: Array<Int>,
    @SerializedName("poster_path") val poster_path: String,
    val overview: String
)