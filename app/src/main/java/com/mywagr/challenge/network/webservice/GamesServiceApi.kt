package com.mywagr.challenge.network.webservice

import com.mywagr.challenge.network.model.GamesItem
import retrofit2.http.GET
import retrofit2.http.Headers

const val GAMES = "code-challenge-mobile-app-games"
internal interface GamesServiceApi {
    @GET(GAMES)
    suspend fun getGames(): List<GamesItem>
}