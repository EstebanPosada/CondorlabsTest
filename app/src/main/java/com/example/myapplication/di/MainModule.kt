package com.example.myapplication.di

import com.example.myapplication.BuildConfig
import com.example.myapplication.app.Constants
import com.example.myapplication.service.database.LocalDataSource
import com.example.myapplication.service.database.RoomDataSource
import com.example.myapplication.service.database.TeamDatabase
import com.example.myapplication.service.repository.*
import com.example.myapplication.ui.MainViewModel
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val appModule = module {
    viewModel { MainViewModel(get(), get()) }

    single { MainRepositoryImpl(get()) as MainRepository }

    single { TeamDatabase.build(androidContext()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
}

val remoteModule = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }

    single { GsonBuilder().registerTypeAdapter(Date::class.java, DateTypeAdapter()).create() }

    single { GsonConverterFactory.create(get()) }

    single {

        OkHttpClient.Builder()
            .connectTimeout(Constants.NETWORK_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Constants.NETWORK_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(get() as HttpLoggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
//            .baseUrl(BuildConfig.BUILD_TYPE)
            .addConverterFactory(get() as GsonConverterFactory)
            .client(get() as OkHttpClient)
            .build()
    }

    single {
        (get() as Retrofit).create(
            SportsApi::class.java
        )
    }
}