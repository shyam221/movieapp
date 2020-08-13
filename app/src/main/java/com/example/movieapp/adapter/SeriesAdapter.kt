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
import com.example.movieapp.model.Series
import com.example.movieapp.room.Favorite
import kotlinx.android.synthetic.main.movie_item.view.*

class SeriesAdapter (private val series: List<Series>, private val favorites: List<Favorite>)
    : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>(){

    var listener: SeriesClickListener? = null

    class SeriesViewHolder(val itemBinding: MovieItemBinding)
        : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SeriesViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.movie_item, parent, false))

    override fun getItemCount() = series.size

    override fun onBindViewHolder(holder: SeriesAdapter.SeriesViewHolder, position: Int) {
        val serie = series[position]

        when {
            serie.vote_average < 4.0 -> holder.itemBinding.rating.text = "D"
            serie.vote_average <= 5.0 -> holder.itemBinding.rating.text = "C"
            serie.vote_average <= 7.0 -> holder.itemBinding.rating.text = "B"
            else -> holder.itemBinding.rating.text = "A"
        }

        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w500"+serie.poster_path).into(holder.itemBinding.coverMovie)
        holder.itemBinding.title.text = serie.name
        holder.itemBinding.releaseDate.text = serie.first_air_date
        holder.itemBinding.overview.text = serie.overview

        for (i in favorites.indices){
            if (favorites[i].title == serie.name){
                holder.itemBinding.fav.visibility = View.VISIBLE
            } else {
                holder.itemBinding.fav.visibility = View.INVISIBLE
            }
        }

        when(serie.genre_ids[0]){
            1 ->{
                if (serie.name.equals("the simpsons",true)){
                    holder.itemBinding.genre.text = "Animation"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FFFF00"))
                } else {
                    holder.itemBinding.genre.text = "Drama"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#0000FF"))
                }
            }
            2 -> {
                if (serie.name.equals("family guy",true)){
                    holder.itemBinding.genre.text = "Animation"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FFFF00"))
                } else {
                    holder.itemBinding.genre.text = "Action"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FF0000"))
                }
            }
            3 -> {
                if (serie.name.equals("the simpsons",true)){
                    holder.itemBinding.genre.text = "Animation"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FFFF00"))
//                holder.itemBinding.border.setBackgroundColor(Color.parseColor("#00FF00"))
                } else {
                    holder.itemBinding.genre.text = "Drama"
                    holder.itemBinding.border.setBackgroundColor(Color.parseColor("#0000FF"))
//                holder.itemBinding.border.setBackgroundColor(Color.parseColor("#00FF00"))
                }
            }
            4 -> {
                holder.itemBinding.genre.text = "Action"
                holder.itemBinding.border.setBackgroundColor(Color.parseColor("#FF0000"))
            }
        }
        holder.itemBinding.content.setOnClickListener {
           if (favorites.isNotEmpty()){
               for (i in favorites.indices){
                   listener?.onSeriesClick(it, serie, favorites[i])
               }
           } else {
               listener?.onSeriesClick(it, serie, null)
           }
        }
    }

}

interface SeriesClickListener{
    fun onSeriesClick(view: View, series: Series, favorite: Favorite?)
}