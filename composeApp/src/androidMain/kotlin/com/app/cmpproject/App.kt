package com.app.cmpproject

import android.app.Application
import com.app.cmpproject.data.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin{
            androidContext(this@App)
        }
    }
}