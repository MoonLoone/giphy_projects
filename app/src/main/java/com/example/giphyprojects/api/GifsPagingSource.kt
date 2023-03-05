package com.example.giphyprojects.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.giphyprojects.model.pojo.Gif
import com.example.giphyprojects.utils.Constants
import com.example.giphyprojects.utils.Constants.START_OFFSET
import retrofit2.HttpException
import java.io.IOException

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
            val gifs = response.body()?.data ?: listOf()
            val nextKey = if (gifs.isEmpty()) {
                null
            } else {
                offset + Constants.STANDART_OFFSET
            }
            LoadResult.Page(
                data = gifs,
                prevKey = if (offset == START_OFFSET) null
                else offset,
                nextKey = nextKey,
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}