package com.example.teste.data

import com.example.teste.data.dao.AppDataBase
import com.example.teste.data.model.CreditCard
import com.example.teste.data.model.Payment
import com.example.teste.data.model.PaymentResult
import com.example.teste.data.model.User
import com.example.teste.data.remote.LiveResources
import com.example.teste.data.remote.RemoteDataSource

class Repository(val remoteDataSource: RemoteDataSource, private val dataBase: AppDataBase) {
    fun getUser(listUser: LiveResources<List<User>>) {
        remoteDataSource.requestListUser(listUser)
    }

    fun sendPayment(payment: Payment, paymentResult: LiveResources<PaymentResult>) {
        remoteDataSource.sendPayment(payment, paymentResult)
    }

    fun getCardDb(): List<CreditCard> {
        return dataBase.creditDao().getCreditCards()
    }

    fun insertCard(creditCard: CreditCard) {
        dataBase.creditDao().insertCreditCard(creditCard)
    }

    fun attCard(creditCard: CreditCard) {
        dataBase.creditDao().updateCreditCard(creditCard)
    }
}