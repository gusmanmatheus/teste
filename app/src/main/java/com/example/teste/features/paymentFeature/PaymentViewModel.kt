package com.example.teste.features.paymentFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teste.data.Repository
import com.example.teste.data.model.CreditCard
import com.example.teste.data.model.Payment
import com.example.teste.data.model.PaymentResult
import com.example.teste.data.model.User
import com.example.teste.data.remote.LiveResources

class PaymentViewModel(val repository: Repository) : ViewModel() {
    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user
    private val _creditCard = MutableLiveData<CreditCard>()
    val creditCard: LiveData<CreditCard> = _creditCard
    var _valuePayment = MutableLiveData<String>()
    var valuePayment: LiveData<String> = _valuePayment
    var paymentResult = LiveResources<PaymentResult>()

    init {
        _valuePayment.value = "0.00"
    }

    fun setCreditCard(creditCard: CreditCard) {
        this._creditCard.value = creditCard
    }

    fun setUser(user: User) {
        this._user.value = user
    }

    fun sendPayment() {
        this._creditCard.value?.let {
            val payment = Payment(
                it.numberCard,
                it.cvvCard,
                _valuePayment.value ?: "0",
                it.expirationDate,
                _user.value?.id?.toInt() ?: 0
            )
            repository.sendPayment(payment,paymentResult)
        }

    }

}