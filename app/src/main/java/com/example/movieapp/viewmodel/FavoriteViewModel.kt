package com.example.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.movieapp.repository.FavoriteRepository
import com.example.movieapp.room.Favorite

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: FavoriteRepository = FavoriteRepository(application)

    fun getFavorite() = repository.getFavorite()

    fun setFavorite(favorite: Favorite) {
        repository.setFavorite(favorite)
    }

}