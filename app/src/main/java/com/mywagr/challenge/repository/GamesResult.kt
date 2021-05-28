package com.mywagr.challenge.repository

import com.mywagr.challenge.network.model.Games

interface  ActionResult

/**
 * Results that comes back from the api layer. Various ui updates can be made w.r.t the type GamesResult
 * that comes back. Repos make respective transformations of the raw data before informing the ui.
 */
sealed class GamesResult: ActionResult

data class OnSuccessGamesResult(val result: Games?, val throwable: Throwable? =null) : GamesResult()

data class OnFailureGamesResult(val throwable: Throwable? =null) : GamesResult()
