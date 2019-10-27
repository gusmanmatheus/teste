package com.example.teste.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id ")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val imagem: String,
    @SerializedName("username")
    val username: String)