package com.example.teste.modules

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
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

fun String.containsNumber(): Boolean {
    this.toRegex()
    for (i in 0..9) {
        if (this.contains("$i")) {
            return true
        }
    }
    return false
}


fun EditText.verifyFieldHasVoids(vararg editText: EditText): Boolean {
    return editText.any { it.text.isEmpty() }
}

fun EditText.changeText(change: () -> Unit) {
    this.addTextChangedListener(
        object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                change()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
}