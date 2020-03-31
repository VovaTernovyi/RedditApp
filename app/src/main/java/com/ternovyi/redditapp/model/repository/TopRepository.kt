package com.ternovyi.redditapp.model.repository

import androidx.lifecycle.liveData
import com.ternovyi.redditapp.extension.LiveResource
import com.ternovyi.redditapp.extension.onError
import com.ternovyi.redditapp.model.container.RedditNews
import com.ternovyi.redditapp.model.container.Resource
import com.ternovyi.redditapp.model.network.contracts.TopContract

interface TopRepository {
    fun getTopEntries(after: String, limit: Int): LiveResource<RedditNews>
    suspend fun getTop(after: String, limit: Int): RedditNews
}

class TopRepositoryImpl(
    private val contract: TopContract
) : TopRepository {

    override fun getTopEntries(after: String, limit: Int): LiveResource<RedditNews> = liveData {
        kotlin.runCatching {
            emit(Resource.loading())
            contract.getTop(after = after, limit = limit)
        }.onSuccess {
            emit(Resource.success(it))
        }.onError {
            emit(Resource.error(it))
        }
    }

    override suspend fun getTop(after: String, limit: Int): RedditNews {
        return contract.getTopEntries(after = after, limit = limit)
    }
}