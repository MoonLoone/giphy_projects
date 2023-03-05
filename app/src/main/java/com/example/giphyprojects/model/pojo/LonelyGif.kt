package com.example.giphyprojects.model.pojo

import com.google.gson.annotations.SerializedName

data class LonelyGif(
    @SerializedName("data")
    val data: Gif? = null,
)