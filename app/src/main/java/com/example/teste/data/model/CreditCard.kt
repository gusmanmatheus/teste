package com.example.teste.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "CreditCard")
data class CreditCard(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "number")
    var numberCard: String,
    @ColumnInfo(name = "name")
    var holderName: String,
    @ColumnInfo(name = "expirationDate")
    var expirationDate: String,
    @ColumnInfo(name = "cvv")
    var cvvCard: String
) : Serializable