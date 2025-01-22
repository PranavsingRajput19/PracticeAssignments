package com.example.onlinebankingapp

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface ApiService {
    @GET("transactions")
    fun getTransactions(): Call<List<Transaction>>

    @GET("transaction/{id}")
    fun getTransactionDetails(@Path("id") transactionId: String): Call<Transaction>
}
