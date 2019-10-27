package com.example.teste.data.model

import com.google.gson.annotations.SerializedName

data class PaymentResult(
    val transaction: Transaction
)

data class Transaction(
    @SerializedName("id")
    val id: Int,
    @SerializedName("value")
    val value: Double,
    @SerializedName("destination_user")
    val destinationUser: User,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("status")
    val status: String
)