package com.example.teste.data.dao

import com.example.teste.data.model.CreditCard

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [CreditCard::class])
abstract class AppDataBase: RoomDatabase() {
    abstract fun creditDao():CreditCardDao
}
