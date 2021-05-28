package com.mywagr.challenge.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mywagr.challenge.R
import com.mywagr.challenge.network.model.GamesItem
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun String.convertToColorHexString(): String {
    return "#${this}"
}

fun String.nameOfClub(): String{
    var res = this.split(" ".toRegex())
    if(res.isEmpty())return this else return  res[res.size - 1]

}

fun String.convertDateToTime():String{
    val dateTime = ZonedDateTime.parse(this)
    val localDateTime: LocalDateTime = dateTime.toLocalDateTime()
    val pattern = "hh:mm a"
    val now: Calendar = Calendar.getInstance()
    val timeZone: TimeZone = now.getTimeZone()
    return "${localDateTime.format(DateTimeFormatter.ofPattern(pattern))} ${timeZone.getDisplayName(true,TimeZone.SHORT)}"
}

class GamesAdapter(val dataSet: List<GamesItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<GamesItem>()
//    init {
//        updateDataSet(dataSet as MutableList<GamesItem>)
//    }

    fun updateDataSet(dataSet: MutableList<GamesItem>) {
        data = dataSet as MutableList<GamesItem>  //use a data method to process data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GamesHeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_view_holder_one, parent, false)
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GamesHeaderViewHolder -> {
                val item = data[position]

                holder.team1.text = item.homeTeam.teamName.nameOfClub()
                holder.team2.text = item.awayTeam.teamName.nameOfClub()
                holder.city1.text = item.homeTeam.city
                holder.city2.text = item.awayTeam.city
                holder.betStatus1.text = if(item.spread.isNullOrBlank())"Odds Pending" else item.spread
                holder.betStatus2.text = if(item.spread.isNullOrBlank())"Odds Pending" else(item.spread.toFloat()*-1).toString()

                holder.div1.setBackgroundColor(Color.parseColor(item.homePrimaryColor.convertToColorHexString()))
                holder.div2.setBackgroundColor(Color.parseColor(item.homeSecondaryColor.convertToColorHexString()))
                holder.div3.setBackgroundColor(Color.parseColor(item.awayPrimaryColor.convertToColorHexString()))
                holder.div4.setBackgroundColor(Color.parseColor(item.awaySecondaryColor.convertToColorHexString()))

                holder.league.text = if (item.league == "ENGLISH_PREMIER_LEAGUE") "EPL" else item.league
                holder.time.text = item.gameDatetime.convertDateToTime()

                val imageIcon = when (item.league) {
                    "NFL" -> R.drawable.ic_nfl
                    "ENGLISH_PREMIER_LEAGUE" -> R.drawable.ic_fifa
                    "NHL" -> R.drawable.ic_nhl
                    "NBA" -> R.drawable.ic_nba
                    else -> R.drawable.ic_nfl
                }
                holder.leagueIcon.setImageResource(imageIcon)
                holder.clock.setImageResource(R.drawable.ic_clock)

            }
            is GamesFooterViewHolder -> {
//                val item = data[position]
//                holder.league.text = item.league
//                holder.time.text = item.gameDatetime
//
//                val imageIcon = when(item.league){
//                    "NFL" ->R.drawable.ic_nfl
//                    "ENGLISH_PREMIER_LEAGUE" ->R.drawable.ic_fifa
//                    "NHL" ->R.drawable.ic_nhl
//                    "NBA" -> R.drawable.ic_nba
//                    else -> R.drawable.ic_nfl
//                }
//                holder.leagueIcon.setImageResource(imageIcon)
//                holder.clock.setImageResource(R.drawable.ic_clock)

            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class GamesHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val parent: View = view.findViewById(R.id.game_detail_row_container)
        val team1: TextView = view.findViewById(R.id.team_1)
        val team2: TextView = view.findViewById(R.id.team_2)
        val city1: TextView = view.findViewById(R.id.city_team_1)
        val city2: TextView = view.findViewById(R.id.city_team_2)
        val betStatus1: TextView = view.findViewById(R.id.bet_status_1)
        val betStatus2: TextView = view.findViewById(R.id.bet_status_2)
        val league: TextView = view.findViewById(R.id.league_name)
        val leagueIcon: ImageView = view.findViewById(R.id.icon_league)
        val time: TextView = view.findViewById(R.id.match_time)
        val clock: ImageView = view.findViewById(R.id.icon_clock)
        val div1: View = view.findViewById(R.id.div1)
        val div2: View = view.findViewById(R.id.div2)
        val div3: View = view.findViewById(R.id.div3)
        val div4: View = view.findViewById(R.id.div4)
    }

    class GamesFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val parent: View = view.findViewById(R.id.game_detail_footer)
        val league: TextView = view.findViewById(R.id.league_name)
        val leagueIcon: ImageView = view.findViewById(R.id.icon_league)
        val time: TextView = view.findViewById(R.id.match_time)
        val clock: ImageView = view.findViewById(R.id.icon_clock)
    }

    companion object {
        const val DEFAULT: Int = 1
        const val ONE: Int = 2
    }
}