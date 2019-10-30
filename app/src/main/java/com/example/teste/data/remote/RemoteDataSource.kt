package com.example.teste.data.remote

import com.example.teste.data.model.Payment
import com.example.teste.data.model.PaymentResult
import com.example.teste.data.model.User
import com.example.teste.data.remote.Resources.Companion.loading
import com.example.teste.data.remote.ServiceProvider.request
import com.example.teste.modules.enqueueLiveResource
import retrofit2.Call


class RemoteDataSource() {
    fun requestListUser(list: LiveResources<List<User>>) {
        list.value = loading()
        val call: Call<List<User>> = request.requestUsers()
        call.enqueueLiveResource(list)
    }

    fun sendPayment(payment: Payment, paymentResult: LiveResources<PaymentResult>) {
        paymentResult.value = loading()
        val call: Call<PaymentResult> = request.sendPayment(payment)
        call.enqueueLiveResource(paymentResult)
    }
}

