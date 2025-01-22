
package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class SourceDetailFragment : Fragment(R.layout.fragment_source_details) {

    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val urlTextView: TextView = view.findViewById(R.id.urlTextView)
        newsAdapter = NewsAdapter { sourceId ->
            Log.d("Source Clicked", "Source ID: $sourceId")
        }
        val source = arguments?.getParcelable<Source>("source")
        if (source != null) {
            displaySourceDetails(source, titleTextView, descriptionTextView, urlTextView)
        } else {
            Log.e("SourceDetailFragment", "Source argument is missing!")
        }

        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getSources("df5a35f38da84a4cb643c4b6d45ad543")
                if (response.isSuccessful) {
                    val sources = response.body()?.sources ?: emptyList()
                    Log.d("API_SUCCESS", "Fetched sources: $sources")
                    newsAdapter.submitList(sources)
                } else {
                    Log.e("API_ERROR", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API_EXCEPTION", "Exception occurred: ${e.message}")
            }
        }
    }

    private fun displaySourceDetails(
        source: Source,
        titleTextView: TextView,
        descriptionTextView: TextView,
        urlTextView: TextView
    ) {
        titleTextView.text = source.name
        descriptionTextView.text = source.description
        urlTextView.text = source.url
    }
}
