package com.example.onlinebankingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.onlinebankingapp.databinding.FragmentTransactionDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionDetailFragment : Fragment() {

    private val args: TransactionDetailFragmentArgs by navArgs()
    private var _binding: FragmentTransactionDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionDetailBinding.inflate(inflater, container, false)

        val transactionId = args.transactionId
        fetchTransactionDetails(transactionId)

        return binding.root
    }

    private fun fetchTransactionDetails(transactionId: String) {
        RetrofitClient.apiService.getTransactionDetails(transactionId)
            .enqueue(object : Callback<Transaction> {
                override fun onResponse(call: Call<Transaction>, response: Response<Transaction>) {
                    if (response.isSuccessful) {
                        response.body()?.let { transaction ->
                            binding.transactionDate.text = transaction.date
                            binding.transactionAmount.text = transaction.amount
                            binding.transactionType.text = transaction.type
                        }
                    }
                }

                override fun onFailure(call: Call<Transaction>, t: Throwable) {
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // To avoid memory leak
    }
}
