package com.ternovyi.redditapp.di.modules

import com.ternovyi.redditapp.view.adapter.TopEntriesPagingAdapter
import org.koin.dsl.module

val KoinOtherModule = module {
    factory { TopEntriesPagingAdapter() }
}