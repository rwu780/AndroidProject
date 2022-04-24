package com.example.umbrella.ui

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umbrella.R
import com.example.umbrella.databinding.FragmentWeatherBinding
import com.example.umbrella.viewmodel.Temperature_Unit
import com.example.umbrella.viewmodel.WeatherListAdapter
import com.example.umbrella.viewmodel.WeatherViewModel

private const val CITY_NAME_KEY = "CITY_NAME"
private const val ZIP_CODE_KEY = "ZIP_CODE"
private const val UNITs_KEY = "UNITS"

class WeatherFragment : Fragment() {

    private val preferenceKey = "com_example_umbrella_data"
    private lateinit var binding : FragmentWeatherBinding
    private val sharedViewModel : WeatherViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)?.let {
            sharedViewModel.setCity(it.getString(CITY_NAME_KEY, "")!!)
            sharedViewModel.setZip(it.getString(ZIP_CODE_KEY, "")!!)
            sharedViewModel.setUnit(it.getString(UNITs_KEY, "")!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onPause() {
        super.onPause()

        val sharedPref = activity?.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()){
            putString(CITY_NAME_KEY, sharedViewModel.city.value)
            putString(ZIP_CODE_KEY, sharedViewModel.zip.value)
            putString(UNITs_KEY, sharedViewModel.unit.value.toString())
        }.apply()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getWeather()

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            weatherList.adapter = WeatherListAdapter(requireContext())
            weatherList.layoutManager = LinearLayoutManager(context)
        }

        sharedViewModel.city.observe(viewLifecycleOwner){
            (requireActivity() as AppCompatActivity).supportActionBar?.title = sharedViewModel.city.value
        }

        sharedViewModel.temperature.observe(viewLifecycleOwner){
            val threshold: Float = when(sharedViewModel.unit.value){
                Temperature_Unit.Kelvin -> 288.70555556f
                Temperature_Unit.Fahrenheit -> 60f
                else -> 15.56f
            }
            if (sharedViewModel.temperature.value!! >= threshold){
                binding.tvTemperature.setBackgroundColor(resources.getColor(R.color.hot_weather))
                binding.tvWeatherDesc.setBackgroundColor(resources.getColor(R.color.hot_weather))
                (requireActivity() as AppCompatActivity)
                    .supportActionBar?.setBackgroundDrawable(
                        ColorDrawable(resources.getColor(R.color.hot_weather)))
            }
            else {
                binding.tvTemperature.setBackgroundColor(resources.getColor(R.color.cold_weather))
                binding.tvWeatherDesc.setBackgroundColor(resources.getColor(R.color.cold_weather))
                (requireActivity() as AppCompatActivity)
                    .supportActionBar?.setBackgroundDrawable(
                        ColorDrawable(resources.getColor(R.color.cold_weather)))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.layout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_setting -> {
                findNavController().navigate(R.id.action_weatherFragment_to_settingFragment)

                return true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }

}