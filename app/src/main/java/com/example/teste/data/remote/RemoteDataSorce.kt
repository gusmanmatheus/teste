package com.example.teste.data.remote

import com.example.teste.data.model.Payment
import com.example.teste.data.model.PaymentResult
import com.example.teste.data.model.User
import com.example.teste.data.remote.ResponseDataSorce.responseEnqueue
import com.example.teste.data.remote.ServiceProvider.request
import retrofit2.Call


class RemoteDataSorce() {
    fun requestListUser(list: LiveResources<List<User>>){
        val call: Call<List<User>> = request.requestUsers()
        responseEnqueue(call, list)
    }
    fun sendPayment(payment: Payment, paymentResult:LiveResources<PaymentResult>){
        val call: Call<PaymentResult> = request.sendPayment(payment)
        responseEnqueue(call,paymentResult)
    }
}