package com.example.teste.data.model

import java.io.Serializable


data class CreditCard(
    var numberCard:String,
    var holderName:String,
    var expirationDate: String,
    var cvvCard: String):Serializable