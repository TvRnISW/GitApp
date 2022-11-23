package com.brainstation.exam

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitRepoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}