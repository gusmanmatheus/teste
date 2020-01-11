package com.example.teste.features.paymentFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teste.data.Repository
import com.example.teste.data.model.*
import com.example.teste.data.remote.LiveResources
import com.example.teste.modules.Utils.cleanMoneyText
import java.util.*
import java.text.SimpleDateFormat


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

    fun setupCreditCard(creditCard: CreditCard) {
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
            val values = cleanMoneyText(_valuePayment.value ?: "").toString()
            val payment = Payment(
                it.numberCard,
                it.cvvCard,
                values,
                it.expirationDate,
                _user.value?.id?.toInt() ?: 0
            )
            repository.sendPayment(payment, paymentResult)
        }

    }

    fun createReceipt(): Receipt {
        val lengthNumCard = _creditCard.value?.numberCard?.length ?: 16

        return Receipt(
            _user.value?.username ?: "",
            _user.value?.image ?: "",
            date(),
            "Transação: "+paymentResult.value?.data?.transaction?.id,
            "Cartão Master "+_creditCard.value?.numberCard?.substring(
                lengthNumCard - 4,
                lengthNumCard - 1
            ),
            valuePayment.value.toString()
        )
    }
    private fun date():String{
        val dateHour = Date()
          return  SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("BR")).format(dateHour).replace(" "," ás ")

    }
}


