package com.example.onlinebankingapp

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

class TransactionListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val transactions = mutableListOf<Transaction>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_transaction_list, container, false)

        recyclerView = binding.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TransactionAdapter(transactions) { transaction ->
            // Navigating with transaction ID
            val action = TransactionListFragmentDirections
                .actionTransactionListFragmentToTransactionDetailFragment(transaction.id)
            findNavController().navigate(action)
        }

        fetchTransactions()

        return binding
    }

    private fun fetchTransactions() {
        RetrofitClient.apiService.getTransactions().enqueue(object : Callback<List<Transaction>> {
            override fun onResponse(call: Call<List<Transaction>>, response: Response<List<Transaction>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        transactions.clear()
                        transactions.addAll(it)
                        recyclerView.adapter?.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
            }
        })
    }
}
