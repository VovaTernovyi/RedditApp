package com.ternovyi.redditapp.model.container

import com.google.gson.annotations.SerializedName

data class RedditNewsData(
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("num_comments")
    val numComments: Int,
    @SerializedName("score")
    val score: Int,
    @SerializedName("created")
    val created: Long,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("subreddit")
    val subReddit: String
)