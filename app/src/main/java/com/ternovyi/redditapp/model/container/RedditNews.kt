package com.ternovyi.redditapp.model.container

import com.google.gson.annotations.SerializedName

data class RedditNews(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("data")
    val redditData: RedditData
)