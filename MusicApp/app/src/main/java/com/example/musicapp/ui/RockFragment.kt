package com.example.musicapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.musicapp.R
import com.example.musicapp.network.Song

import com.example.musicapp.network.SongApi
import com.example.musicapp.network.SongResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "RockFragment"
class RockFragment : Fragment() {

    private lateinit var rvSongList: RecyclerView
    private lateinit var adapter: SongItemAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rock, container, false)

        initViews(view)
        getSongs()

        return view
    }

    private fun getSongs() {
        SongApi.retrofitService.getRock().enqueue(
            object : Callback<SongResponse> {
                override fun onResponse(call: Call<SongResponse>, response: Response<SongResponse>) {
                    if (response.isSuccessful){
                        updateAdapter(response.body())
                    } else{
                        showError(response.message())
                    }
                }
                override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                    showError(t.message ?: "Unknown Error")
                }
            }
        )
    }

    private fun showError(s: String) {
        Log.d(TAG, "showError: $s")

    }

    private fun updateAdapter(body: SongResponse?) {

        body?.let{

            Toast.makeText(context, getString(R.string.toast_msg,it.resultCount.toString()), Toast.LENGTH_SHORT).show()

            it.results.let { songs ->
                adapter = SongItemAdapter(songs) {

                }
                rvSongList.adapter = adapter
            }
        }

    }

    private fun initViews(view: View) {
        rvSongList = view.findViewById(R.id.rv_song_list)
        rvSongList.layoutManager = LinearLayoutManager(context)
        swipeRefreshLayout = view.findViewById(R.id.swipe_container)
        swipeRefreshLayout.setOnRefreshListener {
            getSongs()
            swipeRefreshLayout.isRefreshing = false
        }
    }


}