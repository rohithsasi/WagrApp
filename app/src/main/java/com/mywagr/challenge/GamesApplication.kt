package com.mywagr.challenge
import android.app.Application
import androidx.lifecycle.LifecycleObserver

class GamesApplication : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        application = this
    }


    companion object {
        private lateinit var application: Application
        val APPLICATION by lazy { application }
    }
}