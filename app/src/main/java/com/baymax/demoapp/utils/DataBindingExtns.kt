package com.baymax.demoapp.utils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageUrl")
fun bindImageFromUrl(view: CircleImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .fitCenter()
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}