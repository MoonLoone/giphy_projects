package com.example.giphyprojects.model.pojo

import com.google.gson.annotations.SerializedName

data class OriginalMedia(
    @SerializedName("height")
    val height: String = "",
    @SerializedName("width")
    val width: String = "",
    @SerializedName("url")
    val embedUrl: String? = null,
)
