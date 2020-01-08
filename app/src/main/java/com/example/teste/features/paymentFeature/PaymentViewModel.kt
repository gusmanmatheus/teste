package com.example.teste.features.paymentFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teste.data.model.CreditCard
import com.example.teste.data.model.User

class PaymentViewModel : ViewModel() {
    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user
    private val _creditCard = MutableLiveData<CreditCard>()
    val creditCard: LiveData<CreditCard> = _creditCard
    var _valuePayment = MutableLiveData<String>()
    var valuePayment: LiveData<String> = _valuePayment

    init {
        _valuePayment.value = "0.00"
    }
    fun setCreditCard(creditCard: CreditCard) {
        this._creditCard.value = creditCard
    }

    fun setUser(user: User) {
        this._user.value = user
    }

}