package com.example.giphyprojects.presentation

import androidx.lifecycle.ViewModel
import com.example.giphyprojects.api.RemoteDataSource
import com.example.giphyprojects.api.RetrofitClientObject

class ListFragmentViewModel : ViewModel() {
    fun getGifFromApiByRequest(request: String) =
        RemoteDataSource(RetrofitClientObject).getGifs(request)
}