package com.example.umbrella.viewmodel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umbrella.databinding.WeatherCardItemLayoutBinding
import com.example.umbrella.network.WeatherDaily

private const val TAG = "WeatherListAdapter"
class WeatherListAdapter(val ctx: Context) :RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {

    private var maps: Map<String, List<WeatherDaily>> = emptyMap()

    class WeatherViewHolder(private val binding: WeatherCardItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun onBind(dataItem: String, maps: Map<String, List<WeatherDaily>>, ctx: Context){
                binding.tvDateLabel.text = dataItem
                binding.weatherRv.adapter = WeatherItemAdapter(maps[dataItem]!!)
                binding.weatherRv.layoutManager = GridLayoutManager(ctx, 4)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            WeatherCardItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.onBind(maps.keys.elementAt(position), maps, ctx = ctx)
    }

    override fun getItemCount(): Int {
        return maps.keys.size
    }

    fun updateItems(itemList: List<WeatherDaily>?){
        maps = itemList?.groupBy { it.date }?.filterValues {
            it.isNotEmpty()
        } ?: emptyMap()
        Log.d(TAG, "updateItems: $maps")
        notifyDataSetChanged()

    }
}