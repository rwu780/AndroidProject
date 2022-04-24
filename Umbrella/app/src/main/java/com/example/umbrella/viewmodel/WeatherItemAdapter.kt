package com.example.umbrella.viewmodel

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.umbrella.R
import com.example.umbrella.databinding.WeatherItemLayoutBinding
import com.example.umbrella.network.WeatherDaily
import com.squareup.picasso.Picasso

class WeatherItemAdapter(
    private val ctx: Context,
    private val items: List<WeatherDaily>
) : RecyclerView.Adapter<WeatherItemAdapter.WeatherItemViewHolder>() {

    class WeatherItemViewHolder(private val binding: WeatherItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: WeatherDaily, ctx: Context){
            binding.tvTime.text = item.time
            binding.tvDegree.text = item.temperature.toString()
            Picasso.get().load(item.iconUrl).into(binding.ivIcon)

            if(item.isColdest){
                setTextColor(ctx, R.color.cold_weather)

            }
            if (item.isWarmest){
                setTextColor(ctx, R.color.hot_weather)
            }
        }

        private fun setTextColor(ctx: Context, colorCode: Int) {
            binding.tvTime.setTextColor(ContextCompat.getColor(ctx, colorCode))
            binding.tvDegree.setTextColor(ContextCompat.getColor(ctx, colorCode))

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
        holder.bind(items[position], ctx)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}