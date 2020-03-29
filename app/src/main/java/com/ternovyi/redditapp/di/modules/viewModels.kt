package com.ternovyi.redditapp.di.modules

import com.ternovyi.redditapp.viewModel.TopEntriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val KoinArchitectureComponentViewModels = module {
    viewModel { TopEntriesViewModel(get()) }
}