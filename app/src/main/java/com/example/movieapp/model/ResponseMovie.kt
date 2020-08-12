package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    val page: Int,
//    @SerializedName("total_results") val total_results: Int,
//    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val results: List<Movie>
)