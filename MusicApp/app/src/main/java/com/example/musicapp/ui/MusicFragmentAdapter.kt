package com.example.musicapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicapp.util.TOTAL_FRAGMENT_COUNT


class MusicFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = TOTAL_FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            1 -> ClassicFragment()
            2 -> PopFragment()
            else -> RockFragment()
        }
    }
}