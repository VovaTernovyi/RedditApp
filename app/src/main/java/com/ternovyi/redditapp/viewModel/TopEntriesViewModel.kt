package com.ternovyi.redditapp.viewModel

import androidx.lifecycle.ViewModel
import com.ternovyi.redditapp.model.repository.TopRepository
import org.koin.core.KoinComponent

class TopEntriesViewModel(
    private val repository: TopRepository
) : ViewModel(), KoinComponent {

    val topEntriesLiveData = repository.getTopEntries()
}