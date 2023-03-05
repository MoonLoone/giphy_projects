package com.example.giphyprojects.model.pojo

import com.google.gson.annotations.SerializedName

data class MediaInformation(
    @SerializedName("original")
    val original: OriginalMedia
)