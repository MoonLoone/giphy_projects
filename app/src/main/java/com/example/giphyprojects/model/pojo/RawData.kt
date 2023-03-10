package com.example.giphyprojects.model.pojo

import com.google.gson.annotations.SerializedName

data class RawData(
    @SerializedName("data")
    val data: List<Gif> = listOf<Gif>(),
    @SerializedName("pagination")
    val pagination: Pagination? = null,
)
