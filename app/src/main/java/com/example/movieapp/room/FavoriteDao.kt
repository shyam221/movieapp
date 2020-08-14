package com.example.movieapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavorite(favorite: Favorite)

    @Query("SELECT * from favorite_table ORDER BY title ASC")
    fun getFavorite() : LiveData<List<Favorite>>

    @Query("SELECT * from favorite_table ORDER BY genre_ids ASC")
    fun sortByGenre() : LiveData<List<Favorite>>

    @Query("SELECT EXISTS (SELECT 1 FROM favorite_table WHERE title = :title)")
    fun isFavorit(title: String) : String

    @Query("SELECT * from favorite_table ORDER BY release_date ASC")
    fun sortByRelease() : LiveData<List<Favorite>>

    @Query("SELECT * from favorite_table ORDER BY rating ASC")
    fun sortByRating() : LiveData<List<Favorite>>

    @Query("DELETE FROM favorite_table")
    fun deleteAll()

    @Query("DELETE FROM favorite_table WHERE title = :title")
    fun deleteByTitle(title: String)
}