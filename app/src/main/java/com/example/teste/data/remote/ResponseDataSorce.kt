package com.example.teste.data.remote

import android.util.Log
import com.example.teste.data.remote.Resources.Companion.error
import com.example.teste.data.remote.Resources.Companion.succes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ResponseDataSorce {
    fun <T> responseEnqueue(request: Call<T>, callback: LiveResources<T>) {
        request.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.value = error("ERROR")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.value = succes(response.body())
             //   Log.i("xrl8", "ss" + response.body())

            }
        })
    }
}