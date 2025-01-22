package com.example.fitnessapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.fitnessapp.databinding.FragmentExerciseDetailBinding

class ExerciseDetailFragment : Fragment() {

    private var _binding: FragmentExerciseDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ExerciseDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseDetailBinding.inflate(inflater, container, false)

        fetchExerciseDetails(args.exerciseId)

        return binding.root
    }

    private fun fetchExerciseDetails(exerciseId: String) {
        RetrofitClient.instance.getExerciseDetails(exerciseId).enqueue(object : Callback<ExerciseDetail> {
            override fun onResponse(call: Call<ExerciseDetail>, response: Response<ExerciseDetail>) {
                if (response.isSuccessful) {
                    response.body()?.let { exerciseDetail ->
                        binding.textViewType.text = exerciseDetail.type
                        binding.textViewDuration.text = "Duration: ${exerciseDetail.duration} min"
                        binding.textViewCalories.text = "Calories: ${exerciseDetail.caloriesBurned}"
                        binding.textViewHeartRate.text = "Heart Rate: ${exerciseDetail.heartRate} bpm"
                    }
                }
            }

            override fun onFailure(call: Call<ExerciseDetail>, t: Throwable) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}