package com.example.teste.data.remote

import androidx.lifecycle.MutableLiveData

class Resources<T> private constructor(
    var status: StatusRequest,
    var data: T? = null,
    var error: String? = null
) {

    companion object {
        fun <T> successReponse(data: T?): Resources<T>? {
            return Resources(StatusRequest.SUCCES, data)
        }

        fun  <T>errorResponse(error: String?): Resources<T> {
            return Resources(StatusRequest.ERROR, error = error )
        }

        fun <T> loading(): Resources<T> {
            return Resources(StatusRequest.LOADING)
        }
    }

    enum class StatusRequest {
        SUCCES, ERROR, LOADING
    }

}

class LiveResources<T> : MutableLiveData<Resources<T>>()