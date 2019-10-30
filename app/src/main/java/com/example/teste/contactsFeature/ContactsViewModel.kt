package com.example.teste.contactsFeature

import androidx.lifecycle.ViewModel
import com.example.teste.data.Repository
import com.example.teste.data.model.Payment
import com.example.teste.data.model.PaymentResult
import com.example.teste.data.model.User
import com.example.teste.data.remote.LiveResources

class ContactsViewModel:ViewModel() {
    val listUser = LiveResources<List<User>>()
    val sendPayment = LiveResources<PaymentResult>()

    fun resp(){
        Repository().getUser(listUser)
    }
    fun sendpay(){
        Repository().sendPayment(Payment(
            "1111111111111111",
            "789",
            "79.9",
            "01/018"
            ,1002
        ),sendPayment)
    }

}