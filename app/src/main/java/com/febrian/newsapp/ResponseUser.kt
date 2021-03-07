package com.febrian.newsapp

import com.google.gson.annotations.SerializedName

data class ResponseUser(
        @field:SerializedName("status")
        val status: String? = null,

        @field:SerializedName("totalResults")
        val perPage: Int? = null,

        @field:SerializedName("articles")
        val data: List<DataItem>? = null
)