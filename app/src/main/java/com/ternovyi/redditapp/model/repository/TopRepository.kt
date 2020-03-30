package com.ternovyi.redditapp.model.repository

import androidx.lifecycle.liveData
import com.ternovyi.redditapp.extension.LiveResource
import com.ternovyi.redditapp.extension.onError
import com.ternovyi.redditapp.model.container.Resource
import com.ternovyi.redditapp.model.network.contracts.TopContract
import com.ternovyi.redditapp.model.container.RedditNews

interface TopRepository {
    fun getTopEntries(): LiveResource<RedditNews>
}

class TopRepositoryImpl(
    private val contract: TopContract
) : TopRepository {

    override fun getTopEntries(): LiveResource<RedditNews> = liveData {
        kotlin.runCatching {
            emit(Resource.loading())
            contract.getTop(after = "", limit = 10)
        }.onSuccess {
            emit(Resource.success(it))
        }.onError {
            emit(Resource.error(it))
        }
    }
}