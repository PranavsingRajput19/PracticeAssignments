package com.example.travelapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.travelapp.databinding.FragmentBookingConfirmationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingConfirmationFragment : Fragment() {

    private var _binding: FragmentBookingConfirmationBinding? = null
    private val binding get() = _binding!!
    private val args: BookingConfirmationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingConfirmationBinding.inflate(inflater, container, false)

        // Fetch flight details using Retrofit
        fetchFlightDetails(args.flightId)

        return binding.root
    }

    private fun fetchFlightDetails(flightId: String) {
        RetrofitClient.instance.getFlightDetails(flightId).enqueue(object : Callback<FlightDetails> {
            override fun onResponse(call: Call<FlightDetails>, response: Response<FlightDetails>) {
                if (response.isSuccessful) {
                    val flightDetails = response.body()
                    flightDetails?.let {
                        // Update UI with flight details
                        binding.textViewFlightId.text = it.flightId
                        binding.textViewStatus.text = it.status
                        binding.textViewSeatingAvailability.text = it.seatingAvailability.joinToString { seat -> seat.seatNumber }
                    }
                }
            }

            override fun onFailure(call: Call<FlightDetails>, t: Throwable) {
                // Handle error
                binding.textViewStatus.text = "Error fetching flight details"
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}