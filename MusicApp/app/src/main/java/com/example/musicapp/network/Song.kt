package com.example.musicapp.network

data class Song(
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: String,
    val previewUrl: String
)

data class SongResponse(
    val resultCount: Int,
    val results: List<Song>
)