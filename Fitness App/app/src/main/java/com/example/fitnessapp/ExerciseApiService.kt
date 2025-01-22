package com.example.fitnessapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExerciseApiService {
    @GET("exercises")
    fun getExercises(): Call<List<Exercise>>

    @GET("exercises/{exerciseId}")
    fun getExerciseDetails(@Path("exerciseId") exerciseId: String): Call<ExerciseDetail>
}