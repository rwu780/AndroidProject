package com.example.musicapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.network.Song

import com.squareup.picasso.Picasso

class SongItemAdapter(
    private val dataSet: List<Song>,
    private val openDetails: (Song) -> Unit
) : RecyclerView.Adapter<SongItemAdapter.SongViewHolder>() {

    class SongViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val artistName = view.findViewById<TextView>(R.id.tv_artist_name)
        private val collectionName = view.findViewById<TextView>(R.id.tv_collection_name)
        private val songPrice = view.findViewById<TextView>(R.id.tv_song_price)
        private val artistImage = view.findViewById<ImageView>(R.id.iv_music_image)

        fun bind(song: Song, openDetails: (Song) -> Unit){

            artistName.text = song.artistName
            collectionName.text = song.collectionName
            songPrice.text = view.context.getString(R.string.price, song.trackPrice)
            Picasso.get().load(song.artworkUrl60).into(artistImage)

            view.setOnClickListener{
                openDetails(song)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.music_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val item = dataSet[position]

        holder.bind(item, openDetails)

    }

    override fun getItemCount(): Int = dataSet.size


}