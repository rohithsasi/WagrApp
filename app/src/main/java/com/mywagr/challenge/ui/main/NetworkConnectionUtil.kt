package com.mywagr.challenge.ui.main

import android.content.Context
import android.net.ConnectivityManager


object NetworkConnectionUtil {

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}