package com.example.teste.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


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
