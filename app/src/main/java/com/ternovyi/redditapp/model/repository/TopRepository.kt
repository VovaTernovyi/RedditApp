package com.ternovyi.redditapp.model.repository

import androidx.lifecycle.liveData
import com.ternovyi.redditapp.extension.LiveResourceList
import com.ternovyi.redditapp.extension.onError
import com.ternovyi.redditapp.model.container.Entries
import com.ternovyi.redditapp.model.container.Resource
import com.ternovyi.redditapp.model.network.contracts.TopContract

interface TopRepository {
    fun getTopEntries(): LiveResourceList<Entries>
}

class TopRepositoryImpl(
    private val contract: TopContract
) : TopRepository {

    override fun getTopEntries(): LiveResourceList<Entries> = liveData {
        kotlin.runCatching {
            emit(Resource.loading())
            contract.getTop(limit = 10)
        }.onSuccess {
            emit(Resource.success(it))
        }.onError {
            emit(Resource.error(it))
        }
    }
}