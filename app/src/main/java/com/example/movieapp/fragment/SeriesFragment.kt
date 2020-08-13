package com.example.movieapp.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.SeriesAdapter
import com.example.movieapp.databinding.FragmentSeriesBinding
import com.example.movieapp.viewmodel.SeriesViewModel

/**
 * A simple [Fragment] subclass.
 */
class SeriesFragment : Fragment() {

    lateinit var seriesViewModel: SeriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemBinding = DataBindingUtil.inflate<FragmentSeriesBinding>(inflater, R.layout.fragment_series, container, false)
        seriesViewModel = ViewModelProviders.of(activity!!).get(SeriesViewModel::class.java)

        seriesViewModel.data.observe({ lifecycle }, {
            val seriesAdapter = SeriesAdapter(it.results)
            val recyclerView = itemBinding.listSeries

            recyclerView.apply {
                this.adapter = seriesAdapter
                this.layoutManager = GridLayoutManager(activity!!, 2)
            }
        })

        return itemBinding.root
    }


}
