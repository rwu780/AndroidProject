package com.example.musicapp.util

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.musicapp.network.Song


const val TOTAL_FRAGMENT_COUNT = 3


const val BASE_URL = "https://itunes.apple.com/"

const val END_POINT_CLASSIC = "search?term=classick&amp;media=music&amp;entity=song&amp;limit=50"
const val END_POINT_ROCK = "search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
const val END_POINT_POP = "search?term=pop&amp;media=music&amp;entity=song&amp;limit=50"
