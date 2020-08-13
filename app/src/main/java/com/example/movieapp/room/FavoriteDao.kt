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

    @Query("SELECT * from favorite_table ORDER BY id ASC")
    fun getFavorite() : LiveData<List<Favorite>>

    @Query("DELETE FROM favorite_table")
    fun deleteAll()
}