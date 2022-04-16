package com.example.musicapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.get
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.*
import com.example.musicapp.databinding.ActivityMainBinding
import com.example.musicapp.ui.MusicFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

    }

    private fun initViews() {
        tabLayout = binding.tabLayout
        viewPager2 = binding.pager

        val pagerAdapter = MusicFragmentAdapter(this)
        viewPager2.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager2){ tab, position ->
            viewPager2.setCurrentItem(tab.position, true)
            val (tabText, tabDrawable) = when (position) {
                1 -> Pair(getString(R.string.classic), R.drawable.ic_baseline_dashboard_24)
                2 -> Pair(getString(R.string.pop), R.drawable.ic_baseline_notifications_24)
                else -> Pair(getString(R.string.rock), R.drawable.ic_baseline_home_24)
            }
            tab.text = tabText
            tab.setIcon(tabDrawable)
        }.attach()
    }
}