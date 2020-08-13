package com.example.movieapp.fragment


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.SeriesAdapter
import com.example.movieapp.adapter.SeriesClickListener
import com.example.movieapp.databinding.FragmentSeriesBinding
import com.example.movieapp.model.Series
import com.example.movieapp.room.Favorite
import com.example.movieapp.viewmodel.SeriesViewModel

/**
 * A simple [Fragment] subclass.
 */
class SeriesFragment : Fragment(), SeriesClickListener {

    lateinit var seriesViewModel: SeriesViewModel
    lateinit var itemBinding: FragmentSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_series, container, false)
        seriesViewModel = ViewModelProviders.of(activity!!).get(SeriesViewModel::class.java)
        seriesViewModel.getFavorite()
            ?.observe(this, Observer<List<Favorite>> { this.renderFavorite(it) })

        return itemBinding.root
    }

    override fun onSeriesClick(view: View, series: Series, favorite: Favorite?) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    "Iya"
                ) { _, _ ->
                    if (favorite != null) {
                        if (favorite.title == series.name) {
                            seriesViewModel.deleteFavorite(series.name)
                        } else {
                            seriesViewModel.setFavorite(
                                Favorite(
                                    0, true, series.poster_path,
                                    series.name, series.genre_ids[0], series.first_air_date,
                                    series.vote_average, series.overview, "Series"
                                )
                            )
                        }
                    } else {
                        seriesViewModel.setFavorite(
                            Favorite(
                                0, true, series.poster_path,
                                series.name, series.genre_ids[0], series.first_air_date,
                                series.vote_average, series.overview, "Series"
                            )
                        )
                    }
                }
                setNegativeButton(
                    "Tidak"
                ) { _, _ ->

                }
            }
            builder.setMessage("Apakah Anda ingin memfavorite/unfavorite item ini?")
            builder.create()
        }

        alertDialog!!.show()
    }

    fun renderFavorite(favorite: List<Favorite>?) {
        seriesViewModel.data.observe({ lifecycle }, {
            val seriesAdapter = SeriesAdapter(it.results, favorite!!)
            seriesAdapter.listener = this
            val recyclerView = itemBinding.listSeries

            recyclerView.apply {
                this.adapter = seriesAdapter
                this.layoutManager = GridLayoutManager(activity!!, 2)
            }
        })
    }
}
