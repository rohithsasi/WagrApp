package com.mywagr.challenge

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mywagr.challenge.ui.main.GamesPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var gamesPagerAdapter:GamesPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.wagr_toolbar) as Toolbar)
        supportActionBar?.run {
          title = resources.getString(R.string.games)
        }
        gamesPagerAdapter = GamesPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = gamesPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }
}