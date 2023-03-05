package com.example.giphyprojects.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientObject {

    private const val BASE_URL = "https://api.giphy.com/v1/gifs/"
    const val API_KEY = "NRYc18zCx1NkZywebwMzfDBEUSSlLwDG"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build()
    }

    val apiService = getInstance().create(GifApi::class.java)

}