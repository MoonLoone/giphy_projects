package com.example.giphyprojects.model

import com.example.giphyprojects.model.pojo.Gif
import com.example.giphyprojects.model.pojo.LonelyGif
import com.example.giphyprojects.model.pojo.RawData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GifApi {

    @GET("search")
    suspend fun getAllGifsByRequest(
        @Query("q") request: String,
        @Query("api_key") apiKey: String = RetrofitClientObject.API_KEY,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
    ): Response<RawData>

    @GET("{id}")
    suspend fun getGifById(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = RetrofitClientObject.API_KEY
    ): Response<LonelyGif>
}