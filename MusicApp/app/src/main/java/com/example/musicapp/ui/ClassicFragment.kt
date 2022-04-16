package com.example.musicapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentClassicBinding
import com.example.musicapp.network.Song
import com.example.musicapp.network.SongApi
import com.example.musicapp.network.SongResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ClassicFragment"
class ClassicFragment: Fragment() {

    private lateinit var binding: FragmentClassicBinding
    private lateinit var songList: RecyclerView
    private lateinit var adapter: SongItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClassicBinding.inflate(inflater, container, false)

        initView()
        getSongs()
        return binding.root

    }

    private fun getSongs() {

        SongApi.retrofitService.getClassic().enqueue(object : Callback<SongResponse> {
            override fun onResponse(call: Call<SongResponse>, response: Response<SongResponse>) {
                if (response.isSuccessful)
                    updateAdapter(response.body())
                else
                    showError(response.message())

            }

            override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                Toast.makeText(context, "Unable to load collections", Toast.LENGTH_SHORT).show()
                showError(t.message ?: "Unknown Error")
            }
        })

    }

    private fun updateAdapter(body: SongResponse?) {



        body?.let {
            Toast.makeText(context, getString(R.string.toast_msg, it.resultCount.toString()), Toast.LENGTH_SHORT).show()

            it.results.let { songs ->
                adapter = SongItemAdapter(songs){ song ->
                    activity.playSong(song)

                }
                songList.adapter = adapter
            }
        }

    }

    private fun showError(body: String) {
        Log.d(TAG, "showError: $body")

    }

    private fun initView() {
        songList = binding.rvSongList
        songList.layoutManager = LinearLayoutManager(context)

        binding.swipeContainer.setOnRefreshListener {
            getSongs()
            binding.swipeContainer.isRefreshing = false
        }
    }

    private fun FragmentActivity?.playSong(song: Song) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(song.previewUrl))
        intent.setDataAndType(Uri.parse(song.previewUrl), "video/mp4")
        startActivity(intent)

    }
}


