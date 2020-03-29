package com.ternovyi.redditapp

import android.app.Application
import com.ternovyi.redditapp.di.modules.KoinApiModule
import com.ternovyi.redditapp.di.modules.KoinArchitectureComponentViewModels
import com.ternovyi.redditapp.di.modules.KoinOtherModule
import com.ternovyi.redditapp.di.modules.KoinRepositoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RedditApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDependencyInjector()
    }

    private fun initDependencyInjector() {
        startKoin {
            androidContext(this@RedditApplication)
            modules(
                KoinApiModule,
                KoinRepositoriesModule,
                KoinArchitectureComponentViewModels,
                KoinOtherModule
            )
        }
    }

    companion object {
        lateinit var instance: RedditApplication
            private set
    }
}