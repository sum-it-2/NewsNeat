package com.example.newsneat.Network

import com.example.newsneat.models.NewsModels
import com.example.newsneat.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("everything")
    suspend fun getTopHeadlines(
        @Query("q") query: String = "india",
        @Query("apiKey") apiKey: String = API_KEY
    ) : Response<NewsModels>
}