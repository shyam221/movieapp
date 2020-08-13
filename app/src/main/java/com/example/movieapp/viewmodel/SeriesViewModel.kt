package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.ResponseSeries
import com.example.movieapp.network.ApiMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SeriesViewModel : ViewModel() {
    private val _data = MutableLiveData<ResponseSeries>()
    val data : LiveData<ResponseSeries>
            get() = _data

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private var job = Job()
    private var uiScope = CoroutineScope(job + Dispatchers.Main)

    init {
        _response.value = ""
        initData()
    }

    fun initData() {
        uiScope.launch {
            try {
                val result = ApiMovie.retrofitService.showTvSeries()

                if (result.results.isNotEmpty()) {
                    _data.value = result
                    _response.value = "Fetch Data Berhasil!"
                } else {
                    _response.value = "Data Kosong!"
                }
            } catch (t: Throwable) {
                _response.value = t.toString()
                Log.d("Page", t.toString())
            }
        }
    }
}