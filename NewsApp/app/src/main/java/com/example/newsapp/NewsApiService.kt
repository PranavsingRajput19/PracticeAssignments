package com.example.newsapp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("sources")
    suspend fun getSources(@Query("apiKey")apiKey : String): Response<SourcesResponse>
}
