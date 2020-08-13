package com.example.movieapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.FavoriteAdapter
import com.example.movieapp.databinding.ActivityFavoriteBinding
import com.example.movieapp.room.Favorite
import com.example.movieapp.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private var favoriteViewModel: FavoriteViewModel? = null
    lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorite)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        favoriteViewModel?.getFavorite()?.observe(this, Observer<List<Favorite>> { this.renderFavorite(it) })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun renderFavorite(favorites: List<Favorite>?) {
        adapter = FavoriteAdapter(favorites!!)
        val layoutManager = GridLayoutManager(this, 2)
        binding.listFav.layoutManager = layoutManager
        binding.listFav.adapter = adapter
    }
}
