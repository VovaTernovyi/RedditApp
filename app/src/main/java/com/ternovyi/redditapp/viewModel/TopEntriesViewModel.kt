package com.ternovyi.redditapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ternovyi.redditapp.model.container.RedditChildren
import com.ternovyi.redditapp.model.repository.TopEntriesDataSource
import com.ternovyi.redditapp.model.repository.TopEntriesDataSourceFactory
import com.ternovyi.redditapp.model.repository.TopRepository
import org.koin.core.KoinComponent

class TopEntriesViewModel(
    private val repository: TopRepository
) : ViewModel(), KoinComponent {

    val topEntriesLiveData = repository.getTopEntries(after = "", limit = 10)

    var topEntriesPagedList: LiveData<PagedList<RedditChildren>>
    private var liveDataSource: LiveData<TopEntriesDataSource>

    init {
        val itemDataSourceFactory = TopEntriesDataSourceFactory(viewModelScope, repository)
        liveDataSource = itemDataSourceFactory.topEntriesLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(TopEntriesDataSource.PAGE_SIZE)
            .build()
        topEntriesPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()

    }

}