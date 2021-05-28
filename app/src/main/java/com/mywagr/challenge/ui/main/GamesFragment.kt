package com.mywagr.challenge.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mywagr.challenge.R
import com.mywagr.challenge.network.model.GamesItem
import com.mywagr.challenge.presentation.GamesViewModel
import com.mywagr.challenge.repository.OnFailureGamesResult
import com.mywagr.challenge.repository.OnSuccessGamesResult
import java.time.ZonedDateTime

class GamesFragment : Fragment() {

    private lateinit var gamesViewModel: GamesViewModel

    lateinit var gamesAdapted: GamesAdapter

    lateinit var errorText: TextView

    var tabCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabCount = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        gamesViewModel = ViewModelProvider(this).get(GamesViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val gamesRecycleView: RecyclerView = root.findViewById(R.id.games_list)
        errorText = root.findViewById(R.id.error_text)

        gamesAdapted = GamesAdapter(emptyList())
        gamesRecycleView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = gamesAdapted
        }

        fetchAndUpdateUi()
        if (!NetworkConnectionUtil.isOnline(activity as Context)) {
            throwErrorDialog()
        }

        return root
    }

    override fun onStart() {
        super.onStart()
        gamesViewModel.getGames()
    }

    private fun fetchAndUpdateUi() {
        gamesViewModel.gamesResult.observe(this, {
            when (it) {
                is OnSuccessGamesResult -> {
                    errorText.visibility = View.GONE
                    it.result?.let { it1 ->
                        gamesAdapted.updateDataSet(it1.dataList.filter {
                            it.gameDatetime.convertDate() == when (tabCount) {
                                1 -> "2021-01-21"
                                2 -> "2021-01-22"
                                3 -> "2021-01-23"
                                4 -> "2021-01-24"
                                5 -> "2021-01-25"
                                6 -> "2021-01-26"
                                7 -> "2021-01-27"
                                else -> "2021-01-21"
                            }

                        } as MutableList<GamesItem>)
                    }
                }
                is OnFailureGamesResult -> {
                    throwErrorDialog()
                }
            }
        })
    }

    private fun String.convertDate(): String {
        val dateTime = ZonedDateTime.parse(this)
        return dateTime.toLocalDateTime().toLocalDate().toString()

    }

    private fun throwErrorDialog() {
        MaterialAlertDialogBuilder(activity as Context)
                .setTitle(R.string.network_dialog_title.getString())
                .setMessage(R.string.network_dialog_text.getString())
                .setPositiveButton(R.string.network_dialog_retry.getString()) { dialog, which ->
                    gamesViewModel.getGames()

                    if (NetworkConnectionUtil.isOnline(activity as Context)) {
                        gamesViewModel.getGames()
                    } else {
                        throwErrorDialog()
                    }
                    dialog.dismiss()
                }
                .setNegativeButton(android.R.string.cancel) { dialog, which ->
                    dialog.dismiss()
                }
                .show()
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): GamesFragment {
            return GamesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}