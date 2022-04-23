package com.example.umbrella.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umbrella.R
import com.example.umbrella.databinding.FragmentWeatherBinding
import com.example.umbrella.viewmodel.WeatherListAdapter
import com.example.umbrella.viewmodel.WeatherViewModel



class WeatherFragment : Fragment() {

    private lateinit var binding : FragmentWeatherBinding
    private val sharedViewModel : WeatherViewModel by activityViewModels()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getWeather()

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            weatherFragment = this@WeatherFragment
            weatherList.adapter = WeatherListAdapter(requireContext())
            weatherList.layoutManager = LinearLayoutManager(context)
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