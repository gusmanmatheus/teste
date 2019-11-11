package com.example.teste.modules

import com.example.teste.data.remote.LiveResources
import com.example.teste.data.remote.Resources.Companion.errorResponse
import com.example.teste.data.remote.Resources.Companion.successReponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.enqueueLiveResource(callback: LiveResources<T>) {
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable?) {
            callback.postValue(errorResponse(t?.message))
        }

        override fun onResponse(call: Call<T>, response: Response<T>?) {
            callback.postValue(successReponse(response?.body()))
        }

    })
}