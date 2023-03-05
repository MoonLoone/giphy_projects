package com.example.giphyprojects.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.giphyprojects.model.pojo.Gif
import kotlinx.coroutines.flow.Flow

class RemoteDataSource(private val retrofitClient: RetrofitClientObject) {

    fun getGifs(request: String): Flow<PagingData<Gif>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GifsPagingSource(retrofitClient = retrofitClient, request = request)
            }
        ).flow
    }

}