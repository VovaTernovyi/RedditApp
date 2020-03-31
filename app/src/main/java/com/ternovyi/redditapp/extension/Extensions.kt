package com.ternovyi.redditapp.extension

import android.content.res.Resources
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException

inline fun <T> Result<T>.onError(action: (exception: Throwable) -> Unit) {
    this.exceptionOrNull()?.let {
        if (it !is CancellationException && (it as? HttpException)?.code() != 401)
            action(it)
    }
}

fun convertDpToPixel(dp: Float): Float {
    val metrics = Resources.getSystem().displayMetrics
    val px = dp * (metrics.densityDpi / 160f)
    return Math.round(px).toFloat()
}