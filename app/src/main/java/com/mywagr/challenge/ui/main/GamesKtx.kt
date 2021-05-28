package com.mywagr.challenge.ui.main

import com.mywagr.challenge.GamesApplication

fun Int.getString(): String {
    return GamesApplication.APPLICATION.getString(this)
}
