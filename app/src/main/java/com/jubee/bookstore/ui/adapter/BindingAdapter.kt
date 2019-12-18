package com.jubee.bookstore.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String?) {
    if (url == null) return
    Glide.with(context).load(url).into(this)
}