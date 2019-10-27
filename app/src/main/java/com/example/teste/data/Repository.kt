package com.example.teste.data

import com.example.teste.data.model.Payment
import com.example.teste.data.model.PaymentResult
import com.example.teste.data.model.User
import com.example.teste.data.remote.LiveResources
import com.example.teste.data.remote.RemoteDataSorce

class Repository {

    fun getUser(listUser: LiveResources<List<User>>){
        RemoteDataSorce().requestListUser(listUser)
    }
    fun sendPayment(payment: Payment,paymentResult:LiveResources<PaymentResult>){
        RemoteDataSorce().sendPayment(payment, paymentResult)
    }
}