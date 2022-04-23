package com.example.umbrella

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.umbrella.network.WeatherDaily
import com.example.umbrella.network.WeatherItem
import com.example.umbrella.viewmodel.WeatherListAdapter


@BindingAdapter("listWeather")
fun bindWeatherRecyclerView(recyclerView: RecyclerView, data: List<WeatherDaily>?){
    val adapter = recyclerView.adapter as WeatherListAdapter
    adapter.updateItems(data)
}