package com.example.giphyprojects.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyprojects.model.RetrofitClientObject
import com.example.giphyprojects.model.pojo.Gif
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListFragmentViewModel : ViewModel() {

    var listOfGifs = listOf<Gif>()
    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun getGifFromApiByRequest(request: String = "hello") = effect {
        isLoading.value = true
        val response = RetrofitClientObject.apiService.getAllGifsByRequest(
            request,
        )
        if (response.isSuccessful && response.body() != null){
            listOfGifs = response.body()?.data!!
        }
        isLoading.value = false
    }

    private fun effect(function: suspend () -> Unit) =
        CoroutineScope(Dispatchers.IO).launch {
            function.invoke()
        }


}