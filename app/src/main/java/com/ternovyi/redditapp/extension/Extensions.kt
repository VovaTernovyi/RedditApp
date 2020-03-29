package com.ternovyi.redditapp.extension

import android.content.res.Resources
import com.ternovyi.redditapp.model.network.ApiRest
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

fun String.createImageUrl(width: Int, height: Int) =
    "${getBaseImageUrl()}${ApiRest.UPLOADS}/$this?width=$width&height=$height"

private fun getBaseImageUrl() =
    ApiRest.API_BASE_URL.substring(0, ApiRest.API_BASE_URL.indexOf("api"))