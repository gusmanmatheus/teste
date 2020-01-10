package com.example.teste.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.teste.data.model.CreditCard

@Dao
interface CreditCardDao {
    @Query("SELECT * FROM CREDITCARD")
    fun getCreditCards(): List<CreditCard>

    @Insert
    fun insertCreditCard(creditCard: CreditCard)

    @Update
    fun updateCreditCard(creditCard: CreditCard)
}
