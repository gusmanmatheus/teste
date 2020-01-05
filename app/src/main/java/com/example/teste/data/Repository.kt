package com.example.teste.data

import com.example.teste.data.model.Payment
import com.example.teste.data.model.PaymentResult
import com.example.teste.data.model.User
import com.example.teste.data.remote.LiveResources
import com.example.teste.data.remote.RemoteDataSource

class Repository(val remoteDataSource: RemoteDataSource) {
    fun getUser(listUser: LiveResources<List<User>>) {
        remoteDataSource.requestListUser(listUser)
    }

    fun sendPayment(payment: Payment, paymentResult: LiveResources<PaymentResult>) {
        remoteDataSource.sendPayment(payment, paymentResult)
    }
}