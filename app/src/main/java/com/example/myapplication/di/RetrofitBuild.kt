package com.example.myapplication.di

import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RetrofitBuild(baseUrl: String) {
    val retrofit: Retrofit
    val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

//        val mosh = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
        val gson = GsonBuilder().registerTypeAdapter(Date::class.java, DateTypeAdapter()).create()

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

}