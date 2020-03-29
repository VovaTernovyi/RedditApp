package com.ternovyi.redditapp.extension

import com.ternovyi.redditapp.BuildConfig

inline fun onNotReleaseBuild(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}