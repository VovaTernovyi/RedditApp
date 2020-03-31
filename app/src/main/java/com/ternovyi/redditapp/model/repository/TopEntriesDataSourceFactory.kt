package com.ternovyi.redditapp.model.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ternovyi.redditapp.model.container.RedditChildren
import kotlinx.coroutines.CoroutineScope

class TopEntriesDataSourceFactory(
    private val scope: CoroutineScope,
    private val repository: TopRepository
) : DataSource.Factory<String, RedditChildren>() {

    val topEntriesLiveDataSource = MutableLiveData<TopEntriesDataSource>()

    override fun create(): DataSource<String, RedditChildren> {
        val topDataSource = TopEntriesDataSource(scope, repository)
        topEntriesLiveDataSource.postValue(topDataSource)
        return topDataSource
    }
}