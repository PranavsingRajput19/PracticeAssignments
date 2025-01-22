package com.example.travelapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FlightApiService {
    @GET("flights/{flightId}")
    fun getFlightDetails(@Path("flightId") flightId: String): Call<FlightDetails>
}