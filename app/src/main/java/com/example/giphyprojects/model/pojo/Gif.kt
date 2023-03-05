package com.example.giphyprojects.model.pojo

import com.google.gson.annotations.SerializedName

data class Gif(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("rating")
    val rating: String = "",
    @SerializedName("images")
    val mediaInformation: MediaInformation? = null,
)
