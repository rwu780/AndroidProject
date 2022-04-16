package com.example.musicapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentPopBinding
import com.example.musicapp.network.SongApi

import com.example.musicapp.network.SongResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "PopFragment"

class PopFragment: Fragment() {

    private lateinit var binding: FragmentPopBinding
    private lateinit var songList: RecyclerView
    private lateinit var adapter: SongItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopBinding.inflate(inflater, container, false)

        initViews()
        getSongs()
        return binding.root
    }

    private fun getSongs() {
        SongApi.retrofitService.getPop().enqueue(object : Callback<SongResponse> {
            override fun onResponse(call: Call<SongResponse>, response: Response<SongResponse>) {
                if (response.isSuccessful){
                    updateAdapter(response.body())
                }
                else{
                    showError(response.message())
                }
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
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(song.previewUrl))
                    intent.setDataAndType(Uri.parse(song.previewUrl), "video/mp4")
                    activity?.startActivity(intent)

                }
                songList.adapter = adapter
            }
            

        }

    }

    private fun showError(s: String) {
        Log.d(TAG, "showError: $s")

    }


    private fun initViews() {
        songList = binding.rvSongList
        songList.layoutManager = LinearLayoutManager(context)
        binding.swipeContainer.setOnRefreshListener {
            getSongs()
            binding.swipeContainer.isRefreshing = false
        }
    }
}