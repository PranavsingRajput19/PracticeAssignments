package com.example.travelapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.travelapp.databinding.FragmentFlightSelectionBinding

class FlightSelectionFragment : Fragment() {

    private var _binding: FragmentFlightSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlightSelectionBinding.inflate(inflater, container, false)

        // Simulate flight selection
        binding.buttonSelectFlight.setOnClickListener {
            val flightId = "325565"
            val action = FlightSelectionFragmentDirections.actionFlightSelectionToBookingConfirmation(flightId)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}