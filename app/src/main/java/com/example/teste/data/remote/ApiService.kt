package com.example.teste.data.remote

import com.example.teste.data.model.Payment
import com.example.teste.data.model.PaymentResult
import com.example.teste.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("users")
    fun requestUsers(): Call<List<User>>

    @POST("transaction")
    fun sendPayment(
        @Body payment: Payment
    ): Call<PaymentResult>
}