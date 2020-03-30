package com.ternovyi.redditapp.model.container

import com.google.gson.annotations.SerializedName

data class RedditChildren(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("data")
    val data: RedditNewsData
)