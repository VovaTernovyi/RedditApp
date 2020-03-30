package com.ternovyi.redditapp.model.container

import com.google.gson.annotations.SerializedName

data class RedditData(
    @SerializedName("after")
    val after: String?,
    @SerializedName("before")
    val before: String?,
    @SerializedName("children")
    val children: ArrayList<RedditChildren>
)