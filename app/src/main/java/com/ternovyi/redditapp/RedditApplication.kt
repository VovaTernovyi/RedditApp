package com.ternovyi.redditapp

import android.app.Application
import com.facebook.stetho.Stetho
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

            )
        }
    }

    companion object {
        lateinit var instance: RedditApplication
            private set
    }
}