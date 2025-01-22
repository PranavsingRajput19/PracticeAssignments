package com.example.socialmediaapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{userId}")
    fun getUserProfile(@Path("userId") userId: Int): Call<UserProfile>


}