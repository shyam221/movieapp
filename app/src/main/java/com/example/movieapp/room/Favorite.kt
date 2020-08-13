package com.example.movieapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class Favorite(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "favorited")
    var favorited: Boolean,
    @ColumnInfo(name = "cover")
    var cover: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "genre_ids")
    var genres: Int,
    @ColumnInfo(name = "release_date")
    var releaseDate: String,
    @ColumnInfo(name = "rating")
    var rating: Double,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "type")
    var type: String
)