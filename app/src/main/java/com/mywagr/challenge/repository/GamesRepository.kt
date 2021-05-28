package com.mywagr.challenge.repository

import com.mywagr.challenge.network.api.GamesApi

/**
 * Repository that fetches network data and emits results processed for ui to the view model
 */
interface GamesDataRepository {
    suspend fun getGames(): GamesResult

    companion object {
        fun get(): GamesDataRepository {
            return GamesDataRepositoryImpl
        }
    }
}

internal object GamesDataRepositoryImpl : GamesDataRepository {
    private var gamesApi: GamesApi = GamesApi.get()

    /**
     * Informs the view model of Success/Failure with the respective processed data
     */

    override suspend fun getGames(): GamesResult { //I would ideally parse the GamesResult model to ui model object
        //to keep network model object decoupled from the ui layer. Basically process and filter out only the data
        //the ui needs for rendering from GameResult to a model object used only in the Ui layer. Due to time constraints
        //using the same model in the ui as well for now
        val res = gamesApi.getGames()

        if(res?.dataList?.isEmpty() != true) return  OnSuccessGamesResult(res)
        else return OnFailureGamesResult(Throwable("No Games  Found",Throwable("No Games  Found ")))
    }
}