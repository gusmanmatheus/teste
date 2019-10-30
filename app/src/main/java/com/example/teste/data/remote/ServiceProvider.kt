package com.example.teste.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceProvider {
    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl("http://careers.picpay.com/tests/mobdev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val request: ApiService = retrofit.create(ApiService::class.java)
}