package com.example.giphyprojects.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.giphyprojects.api.RemoteDataSource
import com.example.giphyprojects.api.RetrofitClientObject
import com.example.giphyprojects.model.pojo.Gif
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListFragmentViewModel : ViewModel() {

    var listOfGifs = emptyFlow<PagingData<Gif>>()
    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun getGifFromApiByRequest(request: String = "hello") = effect {
        isLoading.value = true
        listOfGifs = RemoteDataSource(RetrofitClientObject).getGifs(request)
        isLoading.value = false
    }

    private fun effect(function: suspend () -> Unit) =
        CoroutineScope(Dispatchers.IO).launch {
            function.invoke()
        }

}