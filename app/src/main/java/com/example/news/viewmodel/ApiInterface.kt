package com.example.news.viewmodel

import com.example.news.model.Article
import com.example.news.model.Response
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*


interface ApiInterface {

    @GET("v2/top-headlines")
    fun getNews(@Query("country") countryCode: String = "in",
                @Query("apiKey") apiKey: String = "d700f7bc628a410384f41a4497931da0") : Call<Response>

}