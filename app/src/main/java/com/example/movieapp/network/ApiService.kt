package com.example.movieapp.network

import com.example.movieapp.model.ResponseMovie
import com.example.movieapp.model.ResponseSeries
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://private-anon-6c9454bc69-demoprogram.apiary-mock.com/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// API Data
interface ApiService {
    @GET("movies")
    suspend fun showMovies() : ResponseMovie

    @GET("tvs")
    suspend fun showTvSeries() : ResponseSeries
}

object ApiMovie {
    val retrofitService = retrofit.create(ApiService::class.java)
}

