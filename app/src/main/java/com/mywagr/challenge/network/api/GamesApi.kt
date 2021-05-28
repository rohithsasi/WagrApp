package com.mywagr.challenge.network.api

import android.util.Log
import com.mywagr.challenge.network.WagrRestClient
import com.mywagr.challenge.network.model.Games
import com.mywagr.challenge.network.webservice.GamesServiceApi
import com.mywagr.challenge.repository.toGamesResult
import java.lang.Exception

/**
 * Api layer responsible for the network request
 */
internal interface GamesApi {


    suspend fun getGames(): Games?

    companion object {
        fun get(): GamesApi {
            return GamesApiImpl
        }
    }
}

/**
 * Rest client
 */
private val WAGR_SERVICE_API: GamesServiceApi by lazy {
    WagrRestClient.get().gamesServiceApi
}

internal object GamesApiImpl : GamesApi {

    override suspend fun getGames(): Games? {
           try {
               return WAGR_SERVICE_API.getGames().toGamesResult()
           }
           catch (ex :Exception){
               Log.d("Games fetch Exception","${ex.message}")
           }
        return null
    }

}



