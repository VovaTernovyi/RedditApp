package com.ternovyi.redditapp.view.custom

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ternovyi.redditapp.extension.createImageUrl

@BindingAdapter("loadImage")
fun loadRoundImage(img: ImageView, url: String?) = url?.run {

}