package com.example.teste.adapter

import android.icu.text.SimpleDateFormat
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.teste.modules.Utils
import com.example.teste.modules.Utils.maskDate
import com.google.android.material.textfield.TextInputEditText
import java.util.*


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            Glide.with(imageView)
                .load(imgUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun setBindLoading(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

}
