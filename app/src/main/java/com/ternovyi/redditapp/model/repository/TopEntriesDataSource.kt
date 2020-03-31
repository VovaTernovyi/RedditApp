package com.ternovyi.redditapp.model.repository

import androidx.paging.PageKeyedDataSource
import com.ternovyi.redditapp.model.container.RedditChildren
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class TopEntriesDataSource(
    private val scope: CoroutineScope,
    private val repository: TopRepository
) :
    PageKeyedDataSource<String, RedditChildren>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, RedditChildren>
    ) {
        scope.launch {
            val result = repository.getTop(after = "", limit = PAGE_SIZE)
            callback.onResult(
                result.redditData.children,
                null,
                result.redditData.after
            )
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, RedditChildren>
    ) {
        scope.launch {
            val result = repository.getTop(after = params.key, limit = PAGE_SIZE)
            callback.onResult(
                result.redditData.children,
                result.redditData.after
            )
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, RedditChildren>
    ) {

    }

    companion object {

        const val PAGE_SIZE = 10
    }

}