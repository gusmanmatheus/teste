package com.example.teste.features.paymentFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide.init
import com.example.teste.data.Repository
import com.example.teste.data.model.CreditCard
import com.example.teste.data.model.User

class PaymentViewModel(private val repository: Repository) : ViewModel() {
    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user
    private val _creditCard = MutableLiveData<CreditCard>()
    val creditCard: LiveData<CreditCard> = _creditCard
    var _valuePayment = MutableLiveData<String>()
    var valuePayment: LiveData<String> = _valuePayment

    init {
        _valuePayment.value = "0.00"
    }
    fun setupCreditCard(creditCard: CreditCard){
        _creditCard.value = creditCard
    }
    fun setupCreditCard() {
        val creditCards = repository.getCardDb()
        if (creditCards.isNotEmpty()) {
            this._creditCard.value = creditCards[0]
        }
    }
        fun verifyHasCard(): Boolean {
            setupCreditCard()
            return _creditCard.value != null
        }


    fun setUser(user: User) {
        this._user.value = user
    }

}