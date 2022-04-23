package com.example.umbrella.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umbrella.R
import com.example.umbrella.databinding.WeatherItemLayoutBinding
import com.example.umbrella.network.WeatherDaily

class WeatherItemAdapter(
    private val items: List<WeatherDaily>
) : RecyclerView.Adapter<WeatherItemAdapter.WeatherItemViewHolder>() {

    class WeatherItemViewHolder(private val binding: WeatherItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: WeatherDaily){
            binding.tvTime.text = item.time
            binding.tvDegree.text = item.temperature.toString()
            binding.ivIcon.setImageResource(R.drawable.ic_baseline_settings_24)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemViewHolder {
        return WeatherItemViewHolder(WeatherItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: WeatherItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}