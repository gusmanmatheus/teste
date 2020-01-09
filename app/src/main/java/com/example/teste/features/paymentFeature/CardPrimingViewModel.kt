package com.example.teste.features.paymentFeature

import androidx.lifecycle.ViewModel
import com.example.teste.data.model.User

class CardPrimingViewModel():ViewModel() {
    lateinit var user: User

    fun setupUser(user: User){
        this.user = user
    }
}