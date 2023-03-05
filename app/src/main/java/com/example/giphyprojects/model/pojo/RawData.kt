package com.example.giphyprojects.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawData(
    @SerializedName("data")
    val data: List<Gif> = listOf<Gif>()
)