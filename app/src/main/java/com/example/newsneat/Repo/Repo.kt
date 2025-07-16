package com.example.newsneat.Repo

import com.example.newsneat.Network.ApiProvider
import com.example.newsneat.models.NewsModels
import retrofit2.Response

class Repo {

    suspend fun newProvider(
        query: String
    ) : Response<NewsModels> {
        return ApiProvider.providerApi().getTopHeadlines(
            query = query
        )
    }
}