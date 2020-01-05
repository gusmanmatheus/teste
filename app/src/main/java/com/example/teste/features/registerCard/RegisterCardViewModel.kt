package com.example.teste.features.registerCard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teste.data.model.CreditCard
import com.example.teste.modules.containsNumber

class RegisterCardViewModel : ViewModel() {

    private var _card = MutableLiveData<CreditCard>()
    var card: LiveData<CreditCard> = _card


    init {
        _card.postValue(CreditCard("", "", "", ""))
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

    private fun verifyCredCardValide() {
        if (verifyNumeberCharacterCvv(_card.value?.cvvCard ?: "")
            && verifyValidateDate(
                _card.value?.expirationDate ?: ""
            )
            && verifyNameContainsNumber(
                _card.value?.holderName ?: ""
            )
            && verifyNumeberCharacterNumberCard(
                _card.value?.numberCard ?: ""
            )
        ) {
            Log.i("xrl8", "passou")
        }
    }

    fun saveCard() {
        verifyCredCardValide()
    }
//    fun insertCardInDB(card: CreditCard): Boolean {
//
//    }
}