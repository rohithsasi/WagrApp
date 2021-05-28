package com.mywagr.challenge.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mywagr.challenge.network.webservice.GamesServiceApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Rest client, interceptors and adapters
 */
internal interface WagrRestClient {
    val gamesServiceApi: GamesServiceApi

    companion object {
        fun get(): WagrRestClient {
            return WagrRestClientImpl
        }
    }
}

private object WagrRestClientImpl : WagrRestClient {

    override val gamesServiceApi: GamesServiceApi
        get() {
            return getGamesApi()
        }

    private var gamesService: GamesServiceApi? = null
    
    private fun getGamesApi(): GamesServiceApi =  gamesService ?: gamesApiBuilder(GamesServiceApi::class.java).apply { gamesService = this }


    private fun <T> gamesApiBuilder(clz: Class<T>, baseUrl: String = "https://us-central1-wagr-develop.cloudfunctions.net"): T {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
                .create(clz)
    }

    private fun getHttpClient(): OkHttpClient? {
        return OkHttpClient.Builder().build()

    }

    private val okHttpClient: OkHttpClient?
        get() {
            return getHttpClient()
        }
}
