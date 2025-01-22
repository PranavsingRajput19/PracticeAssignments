package com.example.fitnessapp

data class Exercise(
    val id: String,
    val type: String,
    val duration: Int,
    val caloriesBurned: Int
)

data class ExerciseDetail(
    val id: String,
    val type: String,
    val duration: Int,
    val caloriesBurned: Int,
    val heartRate: Int
)