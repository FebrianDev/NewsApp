package com.febrian.newsapp

import com.google.gson.annotations.SerializedName

data class DataItem (
        @field:SerializedName("title")
        val title:String? = null,

        @field:SerializedName("urlToImage")
        val img:String? = null,

        @field:SerializedName("description")
        val desc:String? = null,

        @field:SerializedName("url")
        val url:String? = null,

        @field:SerializedName("content")
        val content:String? = null
)