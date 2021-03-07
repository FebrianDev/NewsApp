package com.febrian.newsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    fun getListArticles(@Query("q") sources:String, @Query("apiKey") apiKey:String) : Call<ResponseUser>

}