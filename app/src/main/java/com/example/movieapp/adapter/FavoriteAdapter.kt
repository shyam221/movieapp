package com.example.movieapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.room.Favorite

class FavoriteAdapter (private val favorites: List<Favorite>) :
        RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(val itemBinding: MovieItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = FavoriteViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.movie_item, parent, false))

    override fun getItemCount() = favorites.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = favorites[position]

        when {
            favorite.rating < 4.0 -> holder.itemBinding.rating.text = "D"
            favorite.rating <= 5.0 -> holder.itemBinding.rating.text = "C"
            favorite.rating <= 7.0 -> holder.itemBinding.rating.text = "B"
            else -> holder.itemBinding.rating.text = "A"
        }

        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w500"+favorite.cover).into(holder.itemBinding.coverMovie)
        holder.itemBinding.title.text = favorite.title
        holder.itemBinding.releaseDate.text = favorite.releaseDate
        holder.itemBinding.overview.text = favorite.overview
        holder.itemBinding.fav.visibility = View.VISIBLE

        if(favorite.type.equals("movie", true)){
            when(favorite.genres) {
                1 ->{
                    holder.itemBinding.genre.text = "Drama"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#0000FF"))
                }
                2 -> {
                    holder.itemBinding.genre.text = "Lainnya"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FF00FF"))
                }
                3 -> {
                    holder.itemBinding.genre.text = "Comedy"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#00FF00"))
                }
                4 -> {
                    holder.itemBinding.genre.text = "Action"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FF0000"))
                }
            }
        } else {
            when(favorite.genres){
                1 ->{
                    if (favorite.title.equals("the simpsons",true)){
                        holder.itemBinding.genre.text = "Animation"
                        holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FFFF00"))
                    } else {
                        holder.itemBinding.genre.text = "Drama"
                        holder.itemBinding.border.setBackgroundColor(Color.parseColor("#0000FF"))
                    }
                }
                2 -> {
                    if (favorite.title.equals("family guy",true)){
                        holder.itemBinding.genre.text = "Animation"
                        holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FFFF00"))
                    } else {
                        holder.itemBinding.genre.text = "Action"
                        holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FF0000"))
                    }
                }
                3 -> {
                    if (favorite.title.equals("the simpsons",true)){
                        holder.itemBinding.genre.text = "Animation"
                        holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FFFF00"))
                    } else {
                        holder.itemBinding.genre.text = "Drama"
                        holder.itemBinding.border.setBackgroundColor(Color.parseColor("#0000FF"))
                    }
                }
                4 -> {
                    holder.itemBinding.genre.text = "Action"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FF0000"))
                }
            }
        }
    }

}