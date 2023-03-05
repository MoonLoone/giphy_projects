package com.example.giphyprojects.model.pojo

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("total_count")
    val total: Int = 0,
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("offset")
    val offset: Int = 0,
)
