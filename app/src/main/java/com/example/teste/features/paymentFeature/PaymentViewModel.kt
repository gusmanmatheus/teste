package com.example.teste.features.paymentFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide.init
import com.example.teste.data.Repository
import com.example.teste.data.model.CreditCard
import com.example.teste.data.model.Payment
import com.example.teste.data.model.PaymentResult
import com.example.teste.data.model.User
import com.example.teste.data.remote.LiveResources
import com.example.teste.modules.Utils
import com.example.teste.modules.Utils.cleanMoneyText

class PaymentViewModel(private val repository: Repository) : ViewModel() {
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

    fun sendPayment() {
        this._creditCard.value?.let {
            val values = cleanMoneyText(_valuePayment.value?:"").toString()
            val payment = Payment(
                it.numberCard,
                it.cvvCard,
                values,
                it.expirationDate,
                _user.value?.id?.toInt() ?: 0
            )
            repository.sendPayment(payment,paymentResult)
        }

    }

}