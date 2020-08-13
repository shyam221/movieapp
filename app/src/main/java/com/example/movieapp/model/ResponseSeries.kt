package com.example.movieapp.model

import com.google.gson.annotations.SerializedName


data class ResponseSeries(
    val page: Int,
    @SerializedName("results") val results: List<Series>
)