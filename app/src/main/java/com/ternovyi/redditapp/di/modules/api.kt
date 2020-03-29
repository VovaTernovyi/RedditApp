package com.ternovyi.redditapp.di.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.ternovyi.redditapp.BuildConfig
import com.ternovyi.redditapp.extension.onNotReleaseBuild
import com.ternovyi.redditapp.model.network.contracts.TopContract
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val KoinApiModule = module {
    single(definition = { getOkHttpClient() })
    single { getRetrofit(get()) }

    factory { provideRedditTop(get()) }
}

fun provideRedditTop(retrofit: Retrofit): TopContract = retrofit.create(TopContract::class.java)

fun getOkHttpClient(): OkHttpClient =
    with(OkHttpClient.Builder()) {
        followRedirects(false)
        connectTimeout(20, java.util.concurrent.TimeUnit.SECONDS) // default: 10 seconds
        readTimeout(20, java.util.concurrent.TimeUnit.SECONDS) // default: 10 seconds
        writeTimeout(20, java.util.concurrent.TimeUnit.SECONDS) // default: 10 seconds
        onNotReleaseBuild {
            networkInterceptors().add(StethoInterceptor())
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        return build()
    }

fun getRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        )
        .build()