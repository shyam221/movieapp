package com.example.movieapp.fragment


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.adapter.MovieClickListener
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.room.Favorite
import com.example.movieapp.viewmodel.MovieViewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), MovieClickListener {

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie, container, false)
        viewModel = ViewModelProviders.of(activity!!).get(MovieViewModel::class.java)
        viewModel.getFavorite()?.observe(this, Observer<List<Favorite>> { this.renderFavorite(it) })

        return binding.root
    }

    companion object {
        fun newInstance() : MovieFragment{
            val fragment = MovieFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onItemClicked(view: View, movie: Movie, favorite: Favorite?) {
        val builder = AlertDialog.Builder(activity!!)
        builder.setMessage("Apakah Anda ingin memfavorite/unfavorite item ini?")
        builder.setPositiveButton("Iya") {
                _,_ -> run {
            if (favorite != null){
                if (favorite.title == movie.title) {
                    viewModel.deleteFavorite(movie.title)
                } else {
                    viewModel.setFavorite(
                        Favorite(
                            0, true, movie.poster_path,
                            movie.title, movie.genre_ids[0], movie.release_date,
                            movie.vote_average, movie.overview, "Series"
                        )
                    )
                }
            } else {
                viewModel.setFavorite(
                    Favorite(
                        0, true, movie.poster_path,
                        movie.title, movie.genre_ids[0], movie.release_date,
                        movie.vote_average, movie.overview, "Series"
                    )
                )
            }
        }
        }
        builder.setNegativeButton("Tidak") { _, _ ->  }
        builder.show()
    }

    private fun renderFavorite(favorites: List<Favorite>?) {
        viewModel.data.observe({ lifecycle }, {
            val movieAdapter = MovieAdapter(it.results, favorites!!)
            movieAdapter.listener = this
            val recyclerView = binding.listMovie

            recyclerView.apply {
                this.adapter = movieAdapter
                this.layoutManager = GridLayoutManager(activity!!, 2)
            }
        })
    }
}
