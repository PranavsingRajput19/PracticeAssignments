package com.example.travelapp


data class FlightDetails(
    val flightId: String,
    val status: String,
    val seatingAvailability: List<Seat>
)

data class Seat(
    val seatNumber: String,
    val isAvailable: Boolean
)