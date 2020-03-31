package com.ternovyi.redditapp.model.network.contracts

import com.ternovyi.redditapp.model.container.RedditNews
import com.ternovyi.redditapp.model.network.ApiRest
import retrofit2.http.GET
import retrofit2.http.Query

interface TopContract {

    @GET(ApiRest.TOP)
    suspend fun getTop(
        @Query("after") after: String,
        @Query("limit") limit: Int
    ): RedditNews

    @GET(ApiRest.TOP)
    suspend fun getTopEntries(
        @Query("after") after: String,
        @Query("limit") limit: Int
    ): RedditNews
}