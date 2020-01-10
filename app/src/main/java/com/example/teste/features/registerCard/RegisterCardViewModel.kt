package com.example.teste.features.registerCard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teste.data.Repository
import com.example.teste.data.model.CreditCard
import com.example.teste.data.model.User
import com.example.teste.modules.containsNumber
import com.example.teste.modules.repositoryData

class RegisterCardViewModel(private val repository: Repository) : ViewModel() {

    private var _card = MutableLiveData<CreditCard>()
    var card: LiveData<CreditCard> = _card
    lateinit var user: User

    fun userRecovery(user: User) {
        this.user = user
    }

    init {
        _card.postValue(CreditCard(1, "", "", "", ""))
    }

    fun verifyNumeberCharacterCvv(cvv: String) = cvv.length == 3

    fun verifyValidateDate(date: String): Boolean {
        val dates = date.split("/")
        if (dates.size >= 2) {
            return dates[0].toInt() in 1..12
                    && dates[1].toInt() > 20
        }
        return false
    }

    fun verifyNumeberCharacterNumberCard(numberCard: String) = numberCard.length == 16

    fun verifyNameContainsNumber(name: String): Boolean {
        return !name.containsNumber()
    }

    fun saveCard(): Boolean {
        val newCard = repository.getCardDb()
        if (newCard.isNotEmpty()) {
            card.value?.let {
                val cardAtt = it
                 repository.attCard(cardAtt)
                return true
            } ?: run { return false }
        } else {
            card.value?.let {
                repository.insertCard(it)
                return true
            } ?: run {
                return false
            }
        }
    }
}