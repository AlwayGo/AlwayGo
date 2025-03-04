package com.alwaygo.alwaygo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlwayGoApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}