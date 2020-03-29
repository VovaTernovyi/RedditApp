package com.ternovyi.redditapp.model.network.contracts

import com.ternovyi.redditapp.model.container.Entries
import com.ternovyi.redditapp.model.network.ApiRest
import retrofit2.http.GET
import retrofit2.http.Query

interface TopContract {

    @GET(ApiRest.TOP)
    suspend fun getTop(@Query("limit") limit: Int): List<Entries>
}