package com.ternovyi.redditapp.di.modules

import com.ternovyi.redditapp.model.repository.TopRepository
import com.ternovyi.redditapp.model.repository.TopRepositoryImpl
import org.koin.dsl.module

val KoinRepositoriesModule = module {
    single<TopRepository> { TopRepositoryImpl(get()) }
}