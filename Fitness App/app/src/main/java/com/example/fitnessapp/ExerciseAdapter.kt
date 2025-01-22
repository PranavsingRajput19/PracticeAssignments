package com.example.fitnessapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(
    private val exercises: List<Exercise>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeTextView: TextView = itemView.findViewById(R.id.textViewType)
        val durationTextView: TextView = itemView.findViewById(R.id.textViewDuration)
        val caloriesTextView: TextView = itemView.findViewById(R.id.textViewCalories)

        init {
            itemView.setOnClickListener {
                onClick(exercises[adapterPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.typeTextView.text = exercise.type
        holder.durationTextView.text = "Duration: ${exercise.duration} min"
        holder.caloriesTextView.text = "Calories: ${exercise.caloriesBurned}"
    }

    override fun getItemCount(): Int {
        return exercises.size
    }
}