package com.mywagr.challenge.network.model

import com.google.gson.annotations.SerializedName

data class GamesItem(
        @SerializedName("awayPrimaryColor") val awayPrimaryColor: String,
        @SerializedName("awaySecondaryColor") val awaySecondaryColor: String,
        @SerializedName("awayTeam") val awayTeam: AwayTeam,
        @SerializedName("awayTeamKey") val awayTeamKey: String,
        @SerializedName("gameDatetime") val gameDatetime: String,
        @SerializedName("gameId") val gameId: Int,
        @SerializedName("gameStatus") val gameStatus: String,
        @SerializedName("homePrimaryColor") val homePrimaryColor: String,
        @SerializedName("homeSecondaryColor") val homeSecondaryColor: String,
        @SerializedName("homeTeam") val homeTeam: HomeTeam,
        @SerializedName("homeTeamKey") val homeTeamKey: String,
        @SerializedName("league") val league: String,
        @SerializedName("marketSpread") val marketSpread: MarketSpread,
        @SerializedName("sport") val sport: String,
        @SerializedName("spread") val spread: String
)

data class HomeTeam(
        @SerializedName("city") val city: String,
        @SerializedName("primaryColor") val primaryColor: String,
        @SerializedName("secondaryColor") val secondaryColor: String,
        @SerializedName("teamId") val teamId: Int,
        @SerializedName("teamKey") val teamKey: String,
        @SerializedName("teamName") val teamName: String
)


data class MarketSpread(
        @SerializedName("spread") val spread: String
)

data class AwayTeam(
        @SerializedName("city") val city: String,
        @SerializedName("primaryColor") val primaryColor: String,
        @SerializedName("secondaryColor") val secondaryColor: String,
        @SerializedName("teamId") val teamId: Int,
        @SerializedName("teamKey") val teamKey: String,
        @SerializedName("teamName") val teamName: String
)


