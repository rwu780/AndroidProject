package com.example.musicapp.network

import com.example.musicapp.util.BASE_URL
import com.example.musicapp.util.END_POINT_CLASSIC
import com.example.musicapp.util.END_POINT_POP
import com.example.musicapp.util.END_POINT_ROCK
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface SongApiService {

    @GET(END_POINT_ROCK)
    fun getRock(): Call<SongResponse>

    @GET(END_POINT_CLASSIC)
    fun getClassic(): Call<SongResponse>

    @GET(END_POINT_POP)
    fun getPop(): Call<SongResponse>

}

object SongApi {
    val retrofitService : SongApiService by lazy {
        retrofit.create(SongApiService::class.java)

    }
}

