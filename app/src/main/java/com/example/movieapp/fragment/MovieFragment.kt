package com.example.movieapp.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.viewmodel.MovieViewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMovieBinding>(inflater,R.layout.fragment_movie, container, false)
        viewModel = ViewModelProviders.of(activity!!).get(MovieViewModel::class.java)

        viewModel.data.observe({ lifecycle }, {
            val movieAdapter = MovieAdapter(it.results)
            val recyclerView = binding.listMovie

            recyclerView.apply {
                this.adapter = movieAdapter
                this.layoutManager = GridLayoutManager(activity!!, 2)
            }
        })
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
}
