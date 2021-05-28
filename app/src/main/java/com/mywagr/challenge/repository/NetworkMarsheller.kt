package com.mywagr.challenge.repository

import com.mywagr.challenge.network.model.Games
import com.mywagr.challenge.network.model.GamesItem

internal fun List<GamesItem>.toGamesResult(): Games {
    return Games(this)
}



