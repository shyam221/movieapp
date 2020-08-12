package com.example.movieapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movie
import com.example.movieapp.model.ResponseMovie
import com.example.movieapp.network.ApiMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val _data = MutableLiveData<ResponseMovie>()
    val data : LiveData<ResponseMovie>
        get() = _data

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private var job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    init {
        _response.value = ""
        initData()
    }

    fun initData() {
        uiScope.launch {
            try {
                val result = ApiMovie.retrofitService.showMovies()

                if (result.page != null) {
                    _data.value = result
                    _response.value = "Fetch data berhasil!"
                    Log.d("Page", result.page.toString())
                } else {
                    _response.value = "Data kosong!"
                }
            } catch (t: Throwable){
                _response.value = "Tidak ada koneksi internet"
                Log.d("Page", t.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}