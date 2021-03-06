package com.example.movieapp.adapter

import android.graphics.Color
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.model.ResponseMovie
import com.example.movieapp.room.Favorite

class MovieAdapter(private val movie: List<Movie>,
                   private val favorites: List<Favorite>)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var listener: MovieClickListener? = null

    inner class MovieViewHolder (val itemMovieBinding: MovieItemBinding)
        : RecyclerView.ViewHolder(itemMovieBinding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MovieViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.movie_item, parent, false))


    override fun getItemCount() = movie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = movie[position]

        Log.d("Size", favorites.size.toString())

        when {
            movies.vote_average < 4.0 -> holder.itemMovieBinding.rating.text = "D"
            movies.vote_average <= 5.0 -> holder.itemMovieBinding.rating.text = "C"
            movies.vote_average <= 7.0 -> holder.itemMovieBinding.rating.text = "B"
            else -> holder.itemMovieBinding.rating.text = "A"
        }

        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w500"+movies.poster_path).into(holder.itemMovieBinding.coverMovie)
        holder.itemMovieBinding.title.text = movies.title
        holder.itemMovieBinding.releaseDate.text = movies.release_date
        holder.itemMovieBinding.overview.text = movies.overview

//        favorites.forEach {
//            Log.d("TITLE", it.title)
            if (favorites.any { it.title == movies.title } ){
                holder.itemMovieBinding.fav.visibility = View.VISIBLE
            } else {
                holder.itemMovieBinding.fav.visibility = View.INVISIBLE
            }
//        }


        when(movies.genre_ids[0]){
            1 ->{
                holder.itemMovieBinding.genre.text = "Drama"
                holder.itemMovieBinding.border.setBackgroundColor(Color.parseColor("#0000FF"))
            }
            2 -> {
                holder.itemMovieBinding.genre.text = "Lainnya"
                holder.itemMovieBinding.border.setBackgroundColor(Color.parseColor("#FF00FF"))
            }
            3 -> {
                holder.itemMovieBinding.genre.text = "Comedy"
                holder.itemMovieBinding.border.setBackgroundColor(Color.parseColor("#00FF00"))
            }
            4 -> {
                holder.itemMovieBinding.genre.text = "Action"
                holder.itemMovieBinding.border.setBackgroundColor(Color.parseColor("#FF0000"))
            }
        }
        holder.itemMovieBinding.content.setOnClickListener {
            if (favorites.isNotEmpty()){
                    listener?.onItemClicked(it, movies, favorites)
            } else {
                listener?.onItemClicked(it, movies, favorites)
            }
        }

    }

}
interface MovieClickListener {
    fun onItemClicked(view: View, movie: Movie, favorite: List<Favorite>)
}