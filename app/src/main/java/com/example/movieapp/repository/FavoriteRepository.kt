package com.example.movieapp.repository

import android.app.Application
import com.example.movieapp.room.Favorite
import com.example.movieapp.room.FavoriteDao
import com.example.movieapp.room.FavoriteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FavoriteRepository(application: Application): CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var favoriteDao: FavoriteDao?

    init {
        val db = FavoriteDatabase.getDatabase(application)
        favoriteDao = db?.favoriteDao()
    }

    fun getFavorite() = favoriteDao?.getFavorite()

    fun setFavorite(favorite: Favorite) {
        launch { setFavoriteBG(favorite) }
    }

    private suspend fun setFavoriteBG(favorite: Favorite){
        withContext(Dispatchers.IO){
            favoriteDao?.setFavorite(favorite)
        }
    }
}