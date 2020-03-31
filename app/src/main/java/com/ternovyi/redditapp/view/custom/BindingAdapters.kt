package com.ternovyi.redditapp.view.custom

import android.text.format.DateUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ternovyi.redditapp.extension.glideLoadImage

@BindingAdapter("timeAgoFormatter")
fun timeAgoFormatter(textView: TextView, created: Long) {
    val now = System.currentTimeMillis()
    val ago = DateUtils.getRelativeTimeSpanString(created * 1000, now, DateUtils.MINUTE_IN_MILLIS)
    textView.text = ago
}

@BindingAdapter("loadImage")
fun loadImage(img: ImageView, url: String?) = url?.run {
    glideLoadImage(img.context, url, img, 0)
}