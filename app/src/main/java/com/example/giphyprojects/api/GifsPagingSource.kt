package com.example.giphyprojects.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.giphyprojects.model.pojo.Gif
import com.example.giphyprojects.model.pojo.RawData
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

private const val START_OFFSET = 0

class GifsPagingSource(
    private val retrofitClient: RetrofitClientObject,
    private val request: String
) : PagingSource<Int, Gif>() {

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val offset = params.key ?: START_OFFSET
        return try {
            val response = retrofitClient.apiService.getAllGifsByRequest(
             request = request,
             offset = offset
            )
            val gifs = response.body()?.data?: listOf()
            val nextKey = if (gifs.isEmpty()){
                null
            }else{
                offset+25
            }
            LoadResult.Page(
                data = gifs,
                prevKey = if (offset == START_OFFSET) null
            else offset,
                nextKey = nextKey,
            )
        }catch (e: IOException){
            return LoadResult.Error(e)
        }catch (e: HttpException){
            return LoadResult.Error(e)
        }
    }

}