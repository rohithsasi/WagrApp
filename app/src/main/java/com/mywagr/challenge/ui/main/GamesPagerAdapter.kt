package com.mywagr.challenge.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mywagr.challenge.R

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1, //This should come off of data for api or the api should be designed base off of time to get data between a week range.
        R.string.tab_text_2,
        R.string.tab_text_3,
        R.string.tab_text_4,
        R.string.tab_text_5,
        R.string.tab_text_6,
        R.string.tab_text_7
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class GamesPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return GamesFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): String {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 7
    }
}