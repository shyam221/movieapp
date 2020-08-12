package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("name") val title: String,
    @SerializedName("vote_average") val rating: Double,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("genre_ids") val genreId: Array<Int>,
    @SerializedName("poster_path") val cover: String,
    val overview: String
)