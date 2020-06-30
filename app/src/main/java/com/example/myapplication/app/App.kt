package com.example.myapplication.app

import android.app.Application
import com.example.myapplication.di.appModule
import com.example.myapplication.di.remoteModule
import com.example.myapplication.service.database.TeamDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()
            // use the Android context given there
            androidContext(this@App)
            modules(listOf(appModule, remoteModule))
        }
    }
}