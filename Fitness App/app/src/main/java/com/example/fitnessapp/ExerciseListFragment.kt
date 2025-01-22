package com.example.fitnessapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExerciseListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exercise_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        fetchExercises()

        return view
    }

    private fun fetchExercises() {
        RetrofitClient.instance.getExercises().enqueue(object : Callback<List<Exercise>> {
            override fun onResponse(call: Call<List<Exercise>>, response: Response<List<Exercise>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        exerciseAdapter = ExerciseAdapter(it) { exerciseId ->
                            val action = ExerciseListFragmentDirections.actionExerciseListToExerciseDetail(exerciseId)
                            findNavController().navigate(action)
                        }
                        recyclerView.adapter = exerciseAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Exercise>>, t: Throwable) {
                // Handle error
            }
        })
    }
}