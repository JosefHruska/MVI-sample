package com.example.pepah.rxapp

import android.app.Application
import android.support.v7.app.AppCompatDelegate

/**
 * TODO: Add description
 *
 * @author Josef Hruška (josef@stepuplabs.io)
 */
class App : Application() {

    private var mIsInitialized = false
    private var mRunningActivities = 0

    override fun onCreate() {
        super.onCreate()
        gApp = this
        // Configure AppCompat
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    fun isInitialized(): Boolean {
        return mIsInitialized
    }
}

private var gApp: App? = null

fun app(): App {
    return checkNotNull(gApp, { "App not initialized" })
}
